package com.generic.khatabook.model;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.Set;

public final class KhatabookDetails extends RepresentationModel<KhatabookDetails> {
    private final String bookId;
    private final String khatabookId;
    private final int numberOfCustomers;
    private final Set<CustomerDTO> customerDTOS;
    private final KhatabookPaymentSummary paymentSummary;

    public KhatabookDetails(String bookId, String khatabookId, int numberOfCustomers, Set<CustomerDTO> customerDTOS,
                            KhatabookPaymentSummary paymentSummary) {
        this.bookId = bookId;
        this.khatabookId = khatabookId;
        this.numberOfCustomers = numberOfCustomers;
        this.customerDTOS = customerDTOS;
        this.paymentSummary = paymentSummary;
    }


    public KhatabookDetails(final KhatabookDTO khatabookDTO, final Set<CustomerDTO> customerDTOS) {
        this(khatabookDTO.bookId(), khatabookDTO.khatabookId(), customerDTOS.size(), customerDTOS, KhatabookPaymentSummary.empty());
    }

    public KhatabookDetails(final KhatabookDTO khatabookDTO, final Set<CustomerDTO> customerDTOS, final KhatabookPaymentSummary khatabookPaymentSummary) {
        this(khatabookDTO.bookId(), khatabookDTO.khatabookId(), customerDTOS.size(), customerDTOS, khatabookPaymentSummary);
    }

    public KhatabookDetails(final KhatabookDTO khatabook, final CustomerDTO customerDetails, final KhatabookPaymentSummary customerDairy) {
        this(khatabook.bookId(), khatabook.khatabookId(), 1, Set.of(customerDetails), customerDairy);
    }

    public String bookId() {
        return bookId;
    }

    public String khatabookId() {
        return khatabookId;
    }

    public int numberOfCustomers() {
        return numberOfCustomers;
    }

    public Set<CustomerDTO> customerDTOS() {
        return customerDTOS;
    }

    public KhatabookPaymentSummary paymentSummary() {
        return paymentSummary;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (KhatabookDetails) obj;
        return Objects.equals(this.bookId, that.bookId) &&
                Objects.equals(this.khatabookId, that.khatabookId) &&
                this.numberOfCustomers == that.numberOfCustomers &&
                Objects.equals(this.customerDTOS, that.customerDTOS) &&
                Objects.equals(this.paymentSummary, that.paymentSummary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, khatabookId, numberOfCustomers, customerDTOS, paymentSummary);
    }

    @Override
    public String toString() {
        return "KhatabookDetails[" +
                "bookId=" + bookId + ", " +
                "khatabookId=" + khatabookId + ", " +
                "numberOfCustomers=" + numberOfCustomers + ", " +
                "customerDTOS=" + customerDTOS + ", " +
                "paymentSummary=" + paymentSummary + ']';
    }

}
