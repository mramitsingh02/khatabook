package com.generic.khatabook.model;

import java.util.Set;

public record KhatabookDetails(String bookId, String khatabookId, int numberOfCustomers, Set<Customer> customers,
                               KhatabookPaymentSummary paymentSummary) {


    public KhatabookDetails(final Khatabook khatabook, final Set<Customer> customers) {
        this(khatabook.bookId(), khatabook.khatabookId(), customers.size(), customers, KhatabookPaymentSummary.empty());
    }

    public KhatabookDetails(final Khatabook khatabook, final Set<Customer> customers, final KhatabookPaymentSummary khatabookPaymentSummary) {
        this(khatabook.bookId(), khatabook.khatabookId(), customers.size(), customers, khatabookPaymentSummary);
    }
}
