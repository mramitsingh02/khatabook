package com.generic.khatabook.model;

public record KhatabookDTO(String bookId, String khatabookId, String msisdn, String partnerName,
                           String partnerDescription) {


    public KhatabookDTO copyOf(String bookId) {
        return new KhatabookDTO(bookId, this.khatabookId, this.msisdn, this.partnerName, this.partnerDescription);
    }
}
