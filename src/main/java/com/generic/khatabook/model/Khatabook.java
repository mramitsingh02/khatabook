package com.generic.khatabook.model;

public record Khatabook(String bookId, String khatabookId, String msisdn, String partnerName,
                        String partnerDescription) {


    public Khatabook copyOf(String bookId) {
        return new Khatabook(bookId, this.khatabookId, this.msisdn, this.partnerName, this.partnerDescription);
    }
}
