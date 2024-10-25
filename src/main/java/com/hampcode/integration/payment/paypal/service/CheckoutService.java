package com.hampcode.integration.payment.paypal.service;

import com.hampcode.dto.PaymentCaptureResponse;
import com.hampcode.dto.PaymentOrderResponse;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId);

    PaymentCaptureResponse capturePayment(Integer userMembershipId);
}

