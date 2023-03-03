package com.generic.khatabook.model;

import java.util.Set;

public record KhatabookPaymentSummary(AmountDTO total, Set<CustomerPaymentSummary> customers) {
    static KhatabookPaymentSummary empty() {
        return new KhatabookPaymentSummary(AmountDTO.ZERO, Set.of());
    }
}
