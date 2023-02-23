package com.generic.khatabook;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KhatabookController {

//    @Autowired
//    private KhatabookService myKhatabookService;

    @GetMapping("/khatabook/khatabooks")
    public List<Khatabook> khatabookList() {
        return List.of();
    }

    @PostMapping("/khatabook/khatabook")
    public void createKhatabook(@RequestBody Khatabook Khatabook) {


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

        return Khatabook;
    }

}
