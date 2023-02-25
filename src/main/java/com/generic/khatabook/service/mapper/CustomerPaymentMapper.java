package com.generic.khatabook.service.mapper;

import com.generic.khatabook.entity.CustomerPaymentDTO;
import com.generic.khatabook.model.Amount;
import com.generic.khatabook.model.CustomerBySummary;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomerPaymentMapper {


    public static Set<CustomerBySummary> mapToPojos(final Set<CustomerPaymentDTO> customersPayment) {
        return customersPayment.stream().map(CustomerPaymentMapper::mapToPojo).collect(Collectors.toSet());
    }

    private static CustomerBySummary mapToPojo(final CustomerPaymentDTO customerPaymentDTO) {
        return new CustomerBySummary(customerPaymentDTO.getCustomerId(), customerPaymentDTO.getCustomerId(), Amount.of(customerPaymentDTO.getAmount()));
    }
}
