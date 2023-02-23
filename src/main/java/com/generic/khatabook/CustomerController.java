package com.generic.khatabook;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {


//    @Autowired
//    private CustomerService myCustomerService;

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
