package com.generic.khatabook.service.impl;

import com.generic.khatabook.entity.GenerationDate;
import com.generic.khatabook.entity.Khatabook;
import com.generic.khatabook.exceptions.NotFoundException;
import com.generic.khatabook.model.KhatabookDTO;
import com.generic.khatabook.repository.KhatabookRepository;
import com.generic.khatabook.service.KhatabookService;
import com.generic.khatabook.service.mapper.KhatabookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class KhatabookServiceImpl implements KhatabookService {
    @Autowired
    private KhatabookRepository myKhatabookRepository;

    @Override
    public boolean isValid(final KhatabookDTO khatabookDTO) {
        return myKhatabookRepository.exists(Example.of(Khatabook.builder().khatabookId(khatabookDTO.khatabookId()).build()));
    }

    @Override
    public KhatabookDTO get(final String msisdn) {
        return KhatabookMapper.mapToPojo(myKhatabookRepository.findByMsisdn(msisdn).orElseThrow(() -> new RuntimeException("Customer not found.")));
    }

    @Override
    public void create(final KhatabookDTO khatabookDTO) {

        log.info("Khatabook {} created.", khatabookDTO.khatabookId());
        myKhatabookRepository.save(KhatabookMapper.mapToDTO(khatabookDTO, new GenerationDate(LocalDateTime.now(Clock.systemDefaultZone()))));
        log.info("Khatabook {} successful created.", khatabookDTO.khatabookId());
    }

    @Override
    public KhatabookDTO update(final KhatabookDTO khatabookDTO) {
        log.info("Khatabook {} created.", khatabookDTO.khatabookId());
        myKhatabookRepository.save(KhatabookMapper.mapToDTO(khatabookDTO, new GenerationDate(null, LocalDateTime.now(Clock.systemDefaultZone()))));

        log.info("Khatabook {} successful created.", khatabookDTO.khatabookId());
        return getKhatabookByKhatabookId(khatabookDTO.khatabookId());
    }

    @Override
    public KhatabookDTO delete(final String khatabookId, final String msidn) {
        Khatabook customer;
        if (khatabookId != null) {
            customer = myKhatabookRepository.findByKhatabookId(khatabookId).orElseThrow(() -> new NotFoundException(String.format("Customer %s not found.", khatabookId)));
        } else {
            customer = myKhatabookRepository.findByMsisdn(msidn).orElseThrow(() -> new NotFoundException(String.format("Customer %s not found.", msidn)));
        }
        myKhatabookRepository.delete(customer);

        return KhatabookMapper.mapToPojo(customer);
    }

    @Override
    public List<KhatabookDTO> getAll() {

        return myKhatabookRepository.findAll().stream().map(KhatabookMapper::mapToPojo).toList();
    }

    @Override
    public KhatabookDTO getKhatabookByKhatabookId(final String khatabookId) {
        return KhatabookMapper.mapToPojo(myKhatabookRepository.findByKhatabookId(khatabookId).stream().findFirst().orElseThrow(() -> new NotFoundException(khatabookId)));
    }
}
