package com.generic.khatabook.service.mapper;

import com.generic.khatabook.entity.CustomerDTO;
import com.generic.khatabook.model.Customer;

public class CustomerMapper {
    private Customer myCustomer;
    private CustomerDTO myCustomerDTO;

    public CustomerMapper(Customer customer) {
        myCustomer = customer;
    }

    public CustomerMapper(CustomerDTO customerDTO) {
        myCustomerDTO = customerDTO;
    }

    public static Customer mapToPojo(CustomerDTO myCustomerDTO) {

        if (myCustomerDTO == null) {
            return null;
        }


        return new Customer(myCustomerDTO.getCustomerId(), myCustomerDTO.getKhatabookId(), myCustomerDTO.getMsisdn(), myCustomerDTO.getFirstName(), myCustomerDTO.getLastName());
    }

    public static CustomerDTO mapToDTO(Customer myCustomer) {
        if (myCustomer == null) {
            return null;
        }
        return CustomerDTO.builder().customerId(myCustomer.customerId()).khatabookId(myCustomer.khatabookId()).firstName(myCustomer.firstName()).lastName(myCustomer.lastName()).msisdn(myCustomer.msisdn()).build();
    }
}
