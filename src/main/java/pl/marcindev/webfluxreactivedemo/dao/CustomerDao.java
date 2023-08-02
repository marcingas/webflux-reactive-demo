package pl.marcindev.webfluxreactivedemo.dao;

import org.springframework.stereotype.Component;
import pl.marcindev.webfluxreactivedemo.dto.Customer;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {
    private static void sleepExecutor(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getCustomer() {
        return  IntStream.rangeClosed(1,5)
                .peek(CustomerDao::sleepExecutor)
                .peek(i-> System.out.print("count: " + i))
                .mapToObj(i->new Customer(i,"customer"+ i)).
                collect(Collectors.toList());

    }
    public Flux<Customer> getCustomerReactive() {
        return  Flux.range(1,5)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.print("count: " + i))
                .map(i->new Customer(i,"customer"+i));
    }
    public Flux<Customer> getCustomerList() {
        return  Flux.range(1,20)
                .doOnNext(i-> System.out.print("count: " + i))
                .map(i->new Customer(i,"customer"+i));
    }
}
