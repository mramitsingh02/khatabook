package com.generic.khatabook.services.impl;

import com.generic.khatabook.services.IdGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UUIDGeneratorServiceImpl implements IdGeneratorService {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
