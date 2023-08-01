package pl.marcindev.webfluxreactivedemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.marcindev.webfluxreactivedemo.dto.Customer;
import pl.marcindev.webfluxreactivedemo.service.CustomerService;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerService customerservice;

    @GetMapping("/")
    private List<Customer> getAllCustomers() {

        return customerservice.loadAllCustomers();
    }

    @GetMapping(value = "/reactive",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<Customer> getAllCustomersReactive() {

        return customerservice.loadAllCustomersReactive();
    }
}
