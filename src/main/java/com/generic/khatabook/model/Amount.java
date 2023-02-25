package com.generic.khatabook.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public record Amount(BigDecimal value, String unitOfMeasurement, Currency myCurrency) {

    static final Amount ZERO = new Amount();
    static final Amount TEN = new Amount(BigDecimal.TEN);

    public Amount() {
        this(BigDecimal.ZERO, "usd", Currency.getInstance(Locale.getDefault()));

    }

    public Amount(final BigDecimal amount) {
        this(amount, "usd", Currency.getInstance(Locale.getDefault()));
    }

    public static Amount of(final BigDecimal amountValue) {
        return new Amount(amountValue);
    }
}
