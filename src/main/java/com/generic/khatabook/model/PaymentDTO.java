package com.generic.khatabook.model;

public record PaymentDTO(String to, String from, AmountDTO amount) {
}
