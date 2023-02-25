package com.generic.khatabook.model;

import java.util.Set;

public record KhatabookPaymentSummary(Amount total, Set<CustomerBySummary> customers) {
    static KhatabookPaymentSummary empty() {
        return new KhatabookPaymentSummary(Amount.ZERO, Set.of());
    }
}
