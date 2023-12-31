package pl.marcindev.webfluxreactivedemo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.marcindev.webfluxreactivedemo.dao.CustomerDao;
import pl.marcindev.webfluxreactivedemo.dto.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest) {
        Flux<Customer> customerList = customerDao.getCustomerList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        Integer customerId = Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> customerMono = customerDao.getCustomerList().
                filter(c -> c.getId() == customerId)
//                .take(1).single();
                .next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ": " + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);


    }
}
