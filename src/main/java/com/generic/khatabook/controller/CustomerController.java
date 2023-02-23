package com.generic.khatabook.controller;

import com.generic.khatabook.controller.pojo.Customer;
import com.generic.khatabook.services.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {


    //    @Autowired
//    private CustomerService myCustomerService;
    @Autowired
    private IdGeneratorService myIdGeneratorService;

    @GetMapping(path = "khatabook/{khatabookId}/customers")
    public List<Customer> customerList() {
        return Collections.emptyList();
    }

    @PostMapping(path = "khatabook/{khatabookId}/customer")
    public void createCustomer(@PathVariable String khatabookId, @RequestBody Customer customer) {


    }

    @GetMapping(path = "khatabook/{khatabookId}/customer/msisdn/{msisdn}")
    public Customer deleteByMsisdn(@PathVariable String msisdn) {
        return new Customer(msisdn, "dummy", "dummy", "1");
    }

    @GetMapping(path = "khatabook/{khatabookId}/customer/id/{id}")
    public Customer deleteById(@PathVariable Long id) {
        return new Customer("msisdn", "dummy", "dummy", id.toString());
    }

    @PutMapping(path = "khatabook/{khatabookId}/customer")
    public Customer updateCustomer(@RequestBody Customer customer) {

        return customer;
    }


}
