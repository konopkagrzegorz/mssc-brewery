package com.brewery.mssc.brewery.web.controller;

import com.brewery.mssc.brewery.services.CustomerService;
import com.brewery.mssc.brewery.web.model.BeerDTO;
import com.brewery.mssc.brewery.web.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable @NotNull String customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(UUID.fromString(customerId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO saved = customerService.saveNewCustomer(customerDTO);
        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/customer/" + saved.getId().toString());
        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable String customerId, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(UUID.fromString(customerId),customerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable @NotNull String customerId) {
        customerService.deleteCustomer(UUID.fromString(customerId));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<List<String>> errors(ConstraintViolationException e) {
//        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
//
//        e.getConstraintViolations().stream().forEach(error -> errors.add(error.getPropertyPath() + " : " + error.getMessage()));
//
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//    }
}
