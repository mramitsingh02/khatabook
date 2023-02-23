package com.generic.khatabook.controller.pojo;

public record Khatabook(String bookId, String khatabookId, String msisdn, String patnerName, String patnerDescription) {


    public Khatabook copyOf(String bookId) {
        return new Khatabook(bookId, this.khatabookId, this.msisdn,this.patnerName, this.patnerDescription);
    }
}
