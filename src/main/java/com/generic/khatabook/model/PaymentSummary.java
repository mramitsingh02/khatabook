package com.generic.khatabook.model;

public record PaymentSummary(Amount total) {

    public static PaymentSummary ofNull() {
        return new PaymentSummary(new Amount());
    }
}
