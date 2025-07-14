package com.kaleshrikant.webflux.service;

import com.kaleshrikant.webflux.dao.CustomerDao;
import com.kaleshrikant.webflux.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total Execution Time (SYNC) = " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersSteams() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStreams();
        long end = System.currentTimeMillis();
        System.out.println("Total Execution Time (A-SYNC) = " + (end - start));
        return customers;
    }
}
