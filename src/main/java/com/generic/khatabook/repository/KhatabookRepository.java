package com.generic.khatabook.repository;

import com.generic.khatabook.entity.KhatabookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhatabookRepository extends JpaRepository<KhatabookDTO, Long> {
    Optional<KhatabookDTO> findByMsisdn(String msisdn);

    Optional<KhatabookDTO> findByKhatabookId(String khatabookId);
}
