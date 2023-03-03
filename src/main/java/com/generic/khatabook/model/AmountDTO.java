package com.generic.khatabook.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;


public record AmountDTO(BigDecimal value, String unitOfMeasurement, Currency currency) {

    public static final Currency DEFAULT = Currency.getInstance(Locale.getDefault());
    public static final AmountDTO ZERO = new AmountDTO();
    public static final AmountDTO TEN = new AmountDTO(BigDecimal.TEN);

    public AmountDTO() {
        this(BigDecimal.ZERO, DEFAULT.getCurrencyCode(), DEFAULT);

    }

    public AmountDTO(final BigDecimal amount) {
        this(amount, DEFAULT.getCurrencyCode(), DEFAULT);
    }

    public static AmountDTO of(BigDecimal amountValue, String unitOfMeasurement) {
        return new AmountDTO(amountValue, unitOfMeasurement.toUpperCase(Locale.getDefault()), DEFAULT);
    }

    public static AmountDTO of(BigDecimal amountValue, Currency currency) {
        return new AmountDTO(amountValue, currency.getCurrencyCode(), currency);
    }

    public static AmountDTO of(final long value) {
        return new AmountDTO(BigDecimal.valueOf(value));
    }

    public static AmountDTO of(BigDecimal value, String unitOfMeasurement, Currency currency) {
        return new AmountDTO(value, unitOfMeasurement, currency);
    }
}
