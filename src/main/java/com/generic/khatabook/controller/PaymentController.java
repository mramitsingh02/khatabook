package com.generic.khatabook.controller;

import com.generic.khatabook.exceptions.CustomerResponseError;
import com.generic.khatabook.model.Customer;
import com.generic.khatabook.model.KhatabookDetails;
import com.generic.khatabook.service.CustomerService;
import com.generic.khatabook.service.IdGeneratorService;
import com.generic.khatabook.service.KhatabookService;
import com.generic.khatabook.service.PaymentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
public class PaymentController {


    @Autowired
    private CustomerService myCustomerService;

    @Autowired
    private KhatabookService myKhatabookService;

    @Autowired
    private PaymentService myPaymentService;


    @Autowired
    private IdGeneratorService myIdGeneratorService;

    @GetMapping(path = "/khatabook/{khatabookId}/customer/{customerId}/payment")
    public ResponseEntity<?> getKhatabookDetails(@PathVariable String khatabookId, @PathVariable String customerId) {

        final val khatabook = myKhatabookService.getKhatabookByKhatabookId(khatabookId);
        if (Objects.isNull(khatabook)) {
            return ResponseEntity.badRequest().body(new CustomerResponseError(khatabookId + " not fount."));
        }

        KhatabookDetails khatabookDetails = new KhatabookDetails(khatabook, myCustomerService.getAll(), myPaymentService.getPaymentDetailByKhatabookId(khatabookId));


        return ResponseEntity.ok(khatabookDetails);
    }

    @PostMapping(path = "/khatabook/{khatabookId}/customer/{customerId}/payment")
    public ResponseEntity<?> createCustomer(@PathVariable String khatabookId, @RequestBody Customer customer) {

        final val khatabook = myKhatabookService.getKhatabookByKhatabookId(khatabookId);
        if (Objects.isNull(khatabook)) {
            return ResponseEntity.badRequest().body(new CustomerResponseError(khatabookId + " not fount."));
        }

        final val customerRequest = customer.copyOf(myIdGeneratorService.generateId());
        myCustomerService.create(customerRequest);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}").buildAndExpand(customer.customerId()).toUri()).body(customerRequest);
    }


}
