package com.generic.khatabook.service.impl;

import com.generic.khatabook.entity.KhatabookDTO;
import com.generic.khatabook.exceptions.NotFoundException;
import com.generic.khatabook.model.Khatabook;
import com.generic.khatabook.repository.KhatabookRepository;
import com.generic.khatabook.service.KhatabookService;
import com.generic.khatabook.service.mapper.KhatabookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KhatabookServiceImpl implements KhatabookService {
    @Autowired
    private KhatabookRepository myKhatabookRepository;

    @Override
    public boolean isValid(final Khatabook khatabook) {
        return myKhatabookRepository.exists(Example.of(KhatabookDTO.builder().khatabookId(khatabook.khatabookId()).build()));
    }

    @Override
    public Khatabook get(final String msisdn) {
        return KhatabookMapper.mapToPojo(myKhatabookRepository.findByMsisdn(msisdn).orElseThrow(() -> new RuntimeException("Customer not found.")));
    }

    @Override
    public void create(final Khatabook khatabook) {

        log.info("Khatabook {} created.", khatabook.khatabookId());
        myKhatabookRepository.save(KhatabookMapper.mapToDTO(khatabook));
        log.info("Khatabook {} successful created.", khatabook.khatabookId());
    }

    @Override
    public Khatabook update(final Khatabook khatabook) {
        log.info("Khatabook {} created.", khatabook.khatabookId());
        myKhatabookRepository.save(KhatabookMapper.mapToDTO(khatabook));

        log.info("Khatabook {} successful created.", khatabook.khatabookId());
        return getKhatabookByKhatabookId(khatabook.khatabookId());
    }

    @Override
    public Khatabook delete(final String khatabookId, final String msidn) {
        KhatabookDTO customer;
        if (khatabookId != null) {
            customer = myKhatabookRepository.findByKhatabookId(khatabookId).orElseThrow(() -> new NotFoundException(String.format("Customer %s not found.", khatabookId)));
        } else {
            customer = myKhatabookRepository.findByMsisdn(msidn).orElseThrow(() -> new NotFoundException(String.format("Customer %s not found.", msidn)));
        }
        myKhatabookRepository.delete(customer);

        return KhatabookMapper.mapToPojo(customer);
    }

    @Override
    public List<Khatabook> getAll() {

        return myKhatabookRepository.findAll().stream().map(KhatabookMapper::mapToPojo).toList();
    }

    @Override
    public Khatabook getKhatabookByKhatabookId(final String khatabookId) {
        return KhatabookMapper.mapToPojo(myKhatabookRepository.findByKhatabookId(khatabookId).stream().findFirst().orElseThrow(() -> new NotFoundException(khatabookId)));
    }
}
