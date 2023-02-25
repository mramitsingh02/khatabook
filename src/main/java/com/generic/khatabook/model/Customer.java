package com.generic.khatabook.model;

public record Customer(String customerId, String khatabookId, String msisdn, String firstName, String lastName) {
    public Customer copyOf(final String generateId) {
        return new Customer(generateId, this.khatabookId, this.msisdn, this.firstName, this.lastName);
    }
}
