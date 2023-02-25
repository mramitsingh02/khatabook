package com.generic.khatabook.repository;

import com.generic.khatabook.entity.CustomerPaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PaymentRepository extends JpaRepository<CustomerPaymentDTO, Long> {
    Set<CustomerPaymentDTO> findByKhatabookId(String khatabookId);
}
