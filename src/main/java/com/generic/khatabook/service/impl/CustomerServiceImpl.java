package com.generic.khatabook.service.impl;

import com.generic.khatabook.entity.CustomerDTO;
import com.generic.khatabook.model.Customer;
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
    public boolean isValid(Customer customer) {
        return CustomerMapper.mapToPojo(myCustomerRepository.existsByMsisdn(customer.msisdn())) != null;
    }

    @Override
    public Customer get(String msisdn) {
        return CustomerMapper.mapToPojo(myCustomerRepository.findByMsisdn(msisdn));
    }

    @Override
    public void create(Customer customer) {
        myCustomerRepository.save(CustomerMapper.mapToDTO(customer));

    }

    @Override
    public Customer update(Customer customer) {
        return CustomerMapper.mapToPojo(myCustomerRepository.save(CustomerMapper.mapToDTO(customer)));
    }

    @Override
    public Customer delete(Long id, String msidn) {
        CustomerDTO customer;
        if (id != null) {
            customer = myCustomerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found."));
        } else {
            customer = myCustomerRepository.findByMsisdn(msidn);
        }
        myCustomerRepository.delete(customer);

        return CustomerMapper.mapToPojo(customer);
    }

    @Override
    public Set<Customer> getAll() {
        return myCustomerRepository.findAll().stream().map(CustomerMapper::mapToPojo).collect(Collectors.toSet());
    }

}
