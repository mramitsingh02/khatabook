package com.generic.khatabook.service.impl;

import com.generic.khatabook.entity.Customer;
import com.generic.khatabook.model.CustomerDTO;
import com.generic.khatabook.repository.CustomerRepository;
import com.generic.khatabook.service.CustomerService;
import com.generic.khatabook.service.mapper.CustomerMapper;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private EntityManagerFactory myEntityManagerFactory;

    @Autowired
    private CustomerRepository myCustomerRepository;

    @Override
    public boolean isValid(CustomerDTO customerDTO) {
        return CustomerMapper.mapToPojo(myCustomerRepository.existsByMsisdn(customerDTO.msisdn())) != null;
    }

    @Override
    public CustomerDTO getByCustomerId(final String msisdn) {
        return CustomerMapper.mapToPojo(myCustomerRepository.findByCustomerId(msisdn));
    }

    @Override
    public CustomerDTO getByMsisdn(String msisdn) {
        return CustomerMapper.mapToPojo(myCustomerRepository.findByMsisdn(msisdn));
    }

    @Override
    public void create(CustomerDTO customerDTO) {
        myCustomerRepository.save(CustomerMapper.mapToDTO(customerDTO));

    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        return CustomerMapper.mapToPojo(myCustomerRepository.save(CustomerMapper.mapToDTO(customerDTO)));
    }

    @Override
    public CustomerDTO delete(Long id, String msidn) {
        Customer customer;
        if (id != null) {
            customer = myCustomerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found."));
        } else {
            customer = myCustomerRepository.findByMsisdn(msidn);
        }
        myCustomerRepository.delete(customer);

        return CustomerMapper.mapToPojo(customer);
    }

    @Override
    public Set<CustomerDTO> getAll() {
        return myCustomerRepository.findAll().stream().map(CustomerMapper::mapToPojo).collect(Collectors.toSet());
    }

}
