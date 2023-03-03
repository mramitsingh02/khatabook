package com.generic.khatabook.service;

import com.generic.khatabook.model.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface CustomerService {
    boolean isValid(CustomerDTO customerDTO);

    CustomerDTO getByCustomerId(String msisdn);

    CustomerDTO getByMsisdn(String msisdn);

    void create(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO);

    CustomerDTO delete(Long id, String msidn);

    Set<CustomerDTO> getAll();
}
