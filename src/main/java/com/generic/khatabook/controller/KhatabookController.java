package com.generic.khatabook.controller;

import com.generic.khatabook.model.Khatabook;
import com.generic.khatabook.service.IdGeneratorService;
import com.generic.khatabook.service.KhatabookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class KhatabookController {

    private static final String NULL = null;
    @Autowired
    private KhatabookService myKhatabookService;
    @Autowired
    private IdGeneratorService myIdGeneratorService;

    @GetMapping("/khatabook/khatabooks")
    public List<Khatabook> khatabookList() {
        return myKhatabookService.getAll();
    }

    @PostMapping("/khatabook/khatabook")
    public ResponseEntity<Object> createKhatabook(@RequestBody Khatabook khatabook) {

        final val khatabookRequest = khatabook.copyOf(myIdGeneratorService.generateId());

        if (!myKhatabookService.isValid(khatabookRequest)) {
            myKhatabookService.create(khatabookRequest);
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/khatabookId/{khatabookId}").buildAndExpand(khatabookRequest.khatabookId()).toUri()).body(khatabookRequest);
        } else {
            return ResponseEntity.badRequest().body(khatabook);
        }


    }

    @GetMapping("/khatabook/khatabook/msisdn/{msisdn}")
    public Khatabook deleteByMsisdn(@PathVariable String msisdn) {
        return myKhatabookService.delete(NULL, msisdn);
    }

    @GetMapping("/khatabook/khatabook/khatabookId/{khatabookId}")
    public Khatabook getById(@PathVariable String khatabookId) {
        return myKhatabookService.getKhatabookByKhatabookId(khatabookId);
    }


    @DeleteMapping("/khatabook/khatabook/khatabookId/{khatabookId}")
    public Khatabook deleteById(@PathVariable String khatabookId) {
        return myKhatabookService.delete(khatabookId, NULL);
    }

    @PutMapping("/khatabook/khatabook")
    public Khatabook updateKhatabook(@RequestBody Khatabook khatabook) {
        return myKhatabookService.update(khatabook);
    }

}
