package com.generic.khatabook.service;

import com.generic.khatabook.model.KhatabookPaymentSummary;

public interface PaymentService {
    public KhatabookPaymentSummary getPaymentDetailByKhatabookId(String khatabookId);
}
