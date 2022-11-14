package com.sunnyside.Scheduler.controller;

import com.sunnyside.Scheduler.dto.Customer;
import com.sunnyside.Scheduler.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/")
    public Customer getCustomer(@RequestParam Integer customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping("/")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
}
