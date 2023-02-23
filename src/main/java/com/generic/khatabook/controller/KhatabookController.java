package com.generic.khatabook.controller;

import com.generic.khatabook.controller.pojo.Khatabook;
import com.generic.khatabook.services.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KhatabookController {

//    @Autowired
//    private KhatabookService myKhatabookService;

    @Autowired
    private IdGeneratorService myIdGeneratorService;

    @GetMapping("/khatabook/khatabooks")
    public List<Khatabook> khatabookList() {
        return List.of();
    }

    @PostMapping("/khatabook/khatabook")
    public Khatabook createKhatabook(@RequestBody Khatabook khatabook) {

        return khatabook.copyOf(myIdGeneratorService.generateId());
    }

    @GetMapping("/khatabook/khatabook/msisdn/{msisdn}")
    public Khatabook deleteByMsisdn(@PathVariable String msisdn) {
        return null;
    }

    @GetMapping("/khatabook/khatabook/id/{id}")
    public Khatabook deleteById(@PathVariable String khatabookId) {
        return null;
    }

    @PutMapping("/khatabook/khatabook")
    public Khatabook updateKhatabook(@RequestBody Khatabook Khatabook) {
//LOg
        return Khatabook;
    }

}
