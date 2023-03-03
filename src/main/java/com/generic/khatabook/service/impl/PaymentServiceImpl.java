package com.generic.khatabook.service.impl;

import com.generic.khatabook.entity.Amount;
import com.generic.khatabook.entity.CustomerPayment;
import com.generic.khatabook.model.AmountDTO;
import com.generic.khatabook.model.CustomerDTO;
import com.generic.khatabook.model.KhatabookDTO;
import com.generic.khatabook.model.KhatabookPaymentSummary;
import com.generic.khatabook.model.PaymentDTO;
import com.generic.khatabook.model.PaymentType;
import com.generic.khatabook.repository.PaymentRepository;
import com.generic.khatabook.service.PaymentService;
import com.generic.khatabook.service.mapper.AmountMapper;
import com.generic.khatabook.service.mapper.CustomerPaymentMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.BinaryOperator;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository myPaymentRepository;

    @Override
    public KhatabookPaymentSummary getPaymentDetailByKhatabookId(final String khatabookId) {

        final val customersPayment = myPaymentRepository.findByKhatabookId(khatabookId);
        BinaryOperator<AmountDTO> addAmount = AmountMapper::add;
        final AmountDTO total = customersPayment.stream().map(CustomerPayment::getAmount)
                .map(AmountMapper::dto)
                .reduce(AmountDTO.ZERO, addAmount);
        return new KhatabookPaymentSummary(total, CustomerPaymentMapper.mapToPojos(customersPayment));
    }

    @Override
    public boolean savePayment(final KhatabookDTO khatabookDTO, final CustomerDTO customerDTO, final PaymentDTO paymentDTO, final PaymentType paymentType) {

        log.info("Save Payment for khatabook {}", khatabookDTO.khatabookId());
        myPaymentRepository.save(CustomerPayment.builder()
                .khatabookId(khatabookDTO.khatabookId())
                .customerId(customerDTO.customerId())
                .amount(Amount.of(paymentDTO.amount().value(), paymentDTO.amount().unitOfMeasurement()))
                .paymentType(paymentType.name())
                .build());


        return false;
    }

    @Override
    public KhatabookPaymentSummary getPaymentDetailForCustomer(final CustomerDTO customerRequest) {

        final Set<CustomerPayment> allRecordForCustomer = myPaymentRepository.findByKhatabookIdAndCustomerId(customerRequest.khatabookId(), customerRequest.customerId());

        BinaryOperator<AmountDTO> addAmount = AmountMapper::add;
        final AmountDTO total = allRecordForCustomer.stream()
                .map(CustomerPayment::getAmount)
                .map(AmountMapper::dto)
                .reduce(AmountDTO.ZERO, addAmount);
        return new KhatabookPaymentSummary(total, CustomerPaymentMapper.mapToPojos(allRecordForCustomer));
    }
}
