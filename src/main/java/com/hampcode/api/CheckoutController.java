package com.hampcode.api;

import com.hampcode.dto.PaymentCaptureResponse;
import com.hampcode.dto.PaymentOrderResponse;
import com.hampcode.integration.payment.paypal.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")


public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/create")
    public ResponseEntity<PaymentOrderResponse> createPaymentOrder(
            @RequestParam Integer purchaseId,
            @RequestParam String returnUrl,
            @RequestParam String cancelUrl,
            @RequestParam (required= false, defaultValue = "paypal")String paymentProvider){
        PaymentOrderResponse response= checkoutService.createPayment(purchaseId,returnUrl,cancelUrl);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/capture")
    public ResponseEntity<PaymentCaptureResponse> capturePaymentOrder(
            @RequestParam String orderId,
            @RequestParam (required = false, defaultValue = "paypal")String paymentProvider
    ){
        PaymentCaptureResponse response = checkoutService.capturePayment(orderId);

        if(response.isCompleted()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }


}
