package com.kaleshrikant.webflux.handler;

import com.kaleshrikant.webflux.dao.CustomerDao;
import com.kaleshrikant.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest) {
        Flux<Customer> customersList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customersList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest) {
        Integer customerId = Integer.valueOf(serverRequest.pathVariable("input"));
        Mono<Customer> customerMono = customerDao
                .getCustomersList()
                .filter(customer -> customer.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
        return ServerResponse
                .ok()
                .body(saveResponse, String.class);
    }
}
