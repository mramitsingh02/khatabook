package com.generic.khatabook.service;

import com.generic.khatabook.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface CustomerService {
    boolean isValid(Customer customer);

    Customer get(String msisdn);

    void create(Customer customer);

    Customer update(Customer customer);

    Customer delete(Long id, String msidn);

    Set<Customer> getAll();
}
