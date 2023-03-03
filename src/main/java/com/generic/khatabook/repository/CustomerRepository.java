package com.generic.khatabook.repository;

import com.generic.khatabook.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByMsisdn(String msisdn);

    Customer existsByMsisdn(String msisdn);

    Customer findByCustomerId(String customerId);
}
