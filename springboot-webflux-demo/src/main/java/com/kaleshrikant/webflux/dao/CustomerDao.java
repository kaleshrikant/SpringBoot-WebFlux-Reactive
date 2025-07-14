package com.kaleshrikant.webflux.dao;

import com.kaleshrikant.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int sleepTimeInMs) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers() {
        return IntStream
                .rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Processing Count : " + i))
                .mapToObj(i -> new Customer(i, "Customer#" + i))
                .toList();
    }

    public Flux<Customer> getCustomersStreams() {
        return Flux
                .range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new Customer(i, "Customer#" + i))
                .doOnNext(i -> System.out.println("Processing Count : " + i));
    }

    public Flux<Customer> getCustomersList() {
        return Flux
                .range(1, 10)
                .map(i -> new Customer(i, "Customer#" + i))
                .doOnNext(i -> System.out.println("Processing Count : " + i));
    }
}
