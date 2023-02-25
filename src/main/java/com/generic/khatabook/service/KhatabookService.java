package com.generic.khatabook.service;

import com.generic.khatabook.model.Khatabook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KhatabookService {
    boolean isValid(Khatabook customer);

    Khatabook get(String msisdn);

    void create(Khatabook customer);

    Khatabook update(Khatabook customer);

    Khatabook delete(String khatabookId, String msidn);

    List<Khatabook> getAll();

    Khatabook getKhatabookByKhatabookId(String khatabookId);
}
