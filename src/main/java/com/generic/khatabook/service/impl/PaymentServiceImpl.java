package com.generic.khatabook.service.impl;

import com.generic.khatabook.entity.CustomerPaymentDTO;
import com.generic.khatabook.model.Amount;
import com.generic.khatabook.model.KhatabookPaymentSummary;
import com.generic.khatabook.repository.PaymentRepository;
import com.generic.khatabook.service.PaymentService;
import com.generic.khatabook.service.mapper.CustomerPaymentMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository myPaymentRepository;

    @Override
    public KhatabookPaymentSummary getPaymentDetailByKhatabookId(final String khatabookId) {

        final val customersPayment = myPaymentRepository.findByKhatabookId(khatabookId);
        BinaryOperator<BigDecimal> addAmount = BigDecimal::add;
        final BigDecimal total = customersPayment.stream().map(CustomerPaymentDTO::getAmount).reduce(BigDecimal.ZERO, addAmount);
        return new KhatabookPaymentSummary(Amount.of(total), CustomerPaymentMapper.mapToPojos(customersPayment));
    }
}
