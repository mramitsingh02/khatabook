package com.generic.khatabook.controller;

import com.generic.khatabook.entity.AppEntity;
import com.generic.khatabook.exceptions.CustomerResponseError;
import com.generic.khatabook.exceptions.NotFoundException;
import com.generic.khatabook.model.CustomerDTO;
import com.generic.khatabook.model.KhatabookDetails;
import com.generic.khatabook.model.KhatabookPaymentSummary;
import com.generic.khatabook.service.CustomerService;
import com.generic.khatabook.service.IdGeneratorService;
import com.generic.khatabook.service.KhatabookService;
import com.generic.khatabook.service.PaymentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {


    @Autowired
    private CustomerService myCustomerService;

    @Autowired
    private KhatabookService myKhatabookService;

    @Autowired
    private PaymentService myPaymentService;


    @Autowired
    private IdGeneratorService myIdGeneratorService;

    @GetMapping(path = "/khatabook/{khatabookId}/customers")
    public ResponseEntity<?> getKhatabookDetails(@PathVariable String khatabookId) {

        final val khatabook = myKhatabookService.getKhatabookByKhatabookId(khatabookId);
        if (Objects.isNull(khatabook)) {
            return ResponseEntity.badRequest().body(new NotFoundException(AppEntity.KHATABOOK, khatabookId));
        }

        KhatabookDetails khatabookDetails = new KhatabookDetails(khatabook, myCustomerService.getAll(), myPaymentService.getPaymentDetailByKhatabookId(khatabookId));
        return ResponseEntity.ok(khatabookDetails);
    }

    @PostMapping(path = "/khatabook/{khatabookId}/customer")
    public ResponseEntity<?> createCustomer(@PathVariable String khatabookId, @RequestBody CustomerDTO customerDTO) {

        final val khatabook = myKhatabookService.getKhatabookByKhatabookId(khatabookId);
        if (Objects.isNull(khatabook)) {
            return ResponseEntity.badRequest().body(new NotFoundException(AppEntity.KHATABOOK, khatabookId));
        }

        final val customer = customerDTO.copyOf(myIdGeneratorService.generateId());
        myCustomerService.create(customer);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}").buildAndExpand(customerDTO.customerId()).toUri()).body(customer);
    }

    @GetMapping(path = "/khatabook/{khatabookId}/customer/{customerId}")
    public ResponseEntity<?> getKhataBookCustomerDetails(@PathVariable String khatabookId, @PathVariable String customerId) {
        final CustomerDTO customerDetails = myCustomerService.getByCustomerId(customerId);
        if (Objects.isNull(customerDetails)) {
            return ResponseEntity.badRequest().body(new CustomerResponseError(customerId + " not fount."));
        }


        final val khatabook = myKhatabookService.getKhatabookByKhatabookId(customerDetails.khatabookId());
        if (Objects.isNull(khatabook)) {
            return ResponseEntity.badRequest().body(new NotFoundException(AppEntity.KHATABOOK, khatabookId));
        }


        final KhatabookPaymentSummary customerDairy = myPaymentService.getPaymentDetailForCustomer(customerDetails);

        KhatabookDetails khatabookDetails = new KhatabookDetails(khatabook, customerDetails, customerDairy);
        Link linkGiventToCustomer = linkTo(methodOn(PaymentController.class)
                .gavenToCustomer(khatabookId, khatabookDetails.customerDTOS().stream().findFirst().map(CustomerDTO::customerId).orElse(null), null)).withRel("PayTo");

        Link linkReceiveFromCustomer = linkTo(methodOn(PaymentController.class)
                .receiveFromCustomer(khatabookId, khatabookDetails.customerDTOS().stream().findFirst().map(CustomerDTO::customerId).orElse(null), null)).withRel("PayTo");

        EntityModel<KhatabookDetails> entityModel = EntityModel.of(khatabookDetails);
        entityModel.add(linkGiventToCustomer);
        entityModel.add(linkReceiveFromCustomer);
        return ResponseEntity.ok(entityModel);
    }


    @GetMapping(path = "/khatabook/{khatabookId}/customer/msisdn/{msisdn}")
    public CustomerDTO deleteByMsisdn(@PathVariable String msisdn) {
        return new CustomerDTO("", "", msisdn, "dummy", "dummy");
    }

    @DeleteMapping(path = "/khatabook/{khatabookId}/customer/id/{id}")
    public CustomerDTO deleteById(@PathVariable Long id) {
        return new CustomerDTO("", "", "msisdn", "dummy", "dummy");
    }

    @PutMapping(path = "/khatabook/{khatabookId}/customer")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {

        return customerDTO;
    }


}
