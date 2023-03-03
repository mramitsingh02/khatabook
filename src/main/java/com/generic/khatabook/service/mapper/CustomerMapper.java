package com.generic.khatabook.service.mapper;

import com.generic.khatabook.entity.Customer;
import com.generic.khatabook.model.CustomerDTO;

public class CustomerMapper {
    private CustomerDTO myCustomerDTO;
    private Customer myCustomer;

    public CustomerMapper(CustomerDTO customerDTO) {
        myCustomerDTO = customerDTO;
    }

    public CustomerMapper(Customer customer) {
        myCustomer = customer;
    }

    public static CustomerDTO mapToPojo(Customer myCustomer) {

        if (myCustomer == null) {
            return null;
        }


        return new CustomerDTO(myCustomer.getCustomerId(), myCustomer.getKhatabookId(), myCustomer.getMsisdn(), myCustomer.getFirstName(), myCustomer.getLastName());
    }

    public static Customer mapToDTO(CustomerDTO myCustomerDTO) {
        if (myCustomerDTO == null) {
            return null;
        }
        return Customer.builder().customerId(myCustomerDTO.customerId()).khatabookId(myCustomerDTO.khatabookId()).firstName(myCustomerDTO.firstName()).lastName(myCustomerDTO.lastName()).msisdn(myCustomerDTO.msisdn()).build();
    }
}
