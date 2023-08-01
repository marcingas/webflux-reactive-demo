package pl.marcindev.webfluxreactivedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marcindev.webfluxreactivedemo.dao.CustomerDao;
import pl.marcindev.webfluxreactivedemo.dto.Customer;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomer();
        long end = System.currentTimeMillis();
        System.out.println("Total execution: " + (end - start));
        return customers;
    }
    public Flux<Customer> loadAllCustomersReactive() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomerReactive();
        long end = System.currentTimeMillis();
        System.out.println("Total execution: " + (end - start));
        return customers;
    }
}
