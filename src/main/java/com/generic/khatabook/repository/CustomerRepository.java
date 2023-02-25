package com.generic.khatabook.repository;

import com.generic.khatabook.entity.CustomerDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerDTO, Long> {
    CustomerDTO findByMsisdn(String msisdn);

    CustomerDTO existsByMsisdn(String msisdn);
}
