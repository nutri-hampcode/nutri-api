package com.hampcode.service.impl;

import com.hampcode.dto.PaymentCaptureResponse;
import com.hampcode.dto.PaymentOrderResponse;
import com.hampcode.dto.UserMembershipCreateUpdateDTO;
import com.hampcode.dto.UserMembershipDetailsDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.integration.payment.paypal.dto.OrderCaptureResponse;
import com.hampcode.integration.payment.paypal.dto.OrderResponse;
import com.hampcode.integration.payment.paypal.service.CheckoutService;
import com.hampcode.integration.payment.paypal.service.PaypalService;
import com.hampcode.model.entity.UserMembership;
import com.hampcode.service.UserMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PaypalService paypalService;
    private final UserMembershipService userMembershipService;

    @Override
    public PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse = paypalService.createOrder(purchaseId, returnUrl, cancelUrl);
        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return null;
    }

    @Override
    public PaymentCaptureResponse capturePayment(Integer orderId) {
        return null;
    }

    @Override
    public PaymentCaptureResponse capturePayment(String userMembershipId) {
        // Captura la orden de pago con el servicio de PayPal utilizando el ID de la membresía
        OrderCaptureResponse orderCaptureResponse = paypalService.captureOrder(userMembershipId);
        boolean completed = "COMPLETED".equals(orderCaptureResponse.getStatus());

        PaymentCaptureResponse paymentCaptureResponse = new PaymentCaptureResponse();
        paymentCaptureResponse.setCompleted(completed);

        if (completed) {
            System.out.println("AAAAAAAAAAAAAAAAAAA777777");
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            UserMembershipDetailsDTO userMembershipDetailsDTO = userMembershipService.confirmMembership(Integer.parseInt(String.valueOf(purchaseIdStr)));
            paymentCaptureResponse.setPurchaseId(userMembershipDetailsDTO.getId());
        };
        return paymentCaptureResponse;
    }
}
