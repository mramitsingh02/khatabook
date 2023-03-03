package com.generic.khatabook.model;

import java.util.Objects;

public record CustomerDTO(String customerId, String khatabookId, String msisdn, String firstName, String lastName) {
    public static final String ANONYMOUS = "Anonymous";


    public CustomerDTO copyOf(final String generateId) {
        return new CustomerDTO(generateId, this.khatabookId, this.msisdn, this.firstName, this.lastName);
    }


    @Override
    public String toString() {

        if (Objects.nonNull(firstName) && Objects.nonNull(lastName)) {
            return ANONYMOUS + "user with " + msisdn + "" + "and customer id " + customerId + " belong to " + khatabookId + ".";

        }

        return firstName + " " + lastName + " with " + msisdn + "and" + " customer id " + customerId + " belong to " + khatabookId + ".";


    }
}
