package com.hampcode.integration.payment.paypal.service;

import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.integration.payment.paypal.dto.*;
import com.hampcode.model.entity.Membership;
import com.hampcode.model.entity.UserMembership;
import com.hampcode.repository.MembershipRepository;
import com.hampcode.repository.UserMembershipRepository;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PaypalService {
    private final UserMembershipRepository userMembershipRepository;
    @Value("${paypal.client-id}")
    private String clientId;
    @Value("${paypal.client-secret}")
    private String clientSecret;
    @Value("${paypal.api-base}")
    private String apiBase;

    @NonNull
    private MembershipRepository membershipRepository;
    private RestClient paypalClient;
    @PostConstruct
    public void init() {
        paypalClient = RestClient
                .builder()
                .baseUrl(apiBase)
                .build();
    }
    public String getAccessToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        //Realizacion de Solicitudes: enviando yna solicitud POST a la API de PayPal para obtener un token de acceso
        return  Objects.requireNonNull(paypalClient
                                .post()
                                .uri("/v1/oauth2/token")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .header("Authorization", "Basic " + Base64.getEncoder()
                                        .encodeToString((clientId + ":" + clientSecret).getBytes()))
                                .body(body)
                                .retrieve()
                                .toEntity(TokenResponse.class).getBody())
                .getAccessToken();
    }

    public OrderResponse createOrder(Integer userId,String returnUrl, String cancelUrl) {
        UserMembership usermembership = userMembershipRepository.findByUserId(userId)
                .orElseThrow(ResourceNotFoundException::new);

        //construccion de la solicitud de pedido
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIntent("CAPTURE");

        Amount amount = new Amount();
        amount.setCurrencyCode("USD");
        amount.setValue(String.valueOf(usermembership.getMembership().getPrice()));

        PurchaseUnit purchaseUnit = new PurchaseUnit();
        purchaseUnit.setReferenceId(String.valueOf(usermembership.getMembership().getId()));
        purchaseUnit.setAmount(amount);
        orderRequest.setPurchaseUnits(Collections.singletonList(purchaseUnit));

        ApplicationContext applicationContext = ApplicationContext.builder()
                .brandName("membership")
                .returnUrl(returnUrl)
                .cancelUrl(cancelUrl)
                .build();

        orderRequest.setApplicationContext(applicationContext);

        return paypalClient
                .post()
                .uri("/v2/checkout/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .body(orderRequest)
                .retrieve()
                .toEntity(OrderResponse.class)
                .getBody();

    }

    public OrderCaptureResponse captureOrder(String orderId) {
        return paypalClient
                .post()
                .uri("/v2/checkout/orders/" + orderId + "/capture")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .retrieve()
                .toEntity(OrderCaptureResponse.class)
                .getBody();
    }
}



