package com.vinsguru.playground.sec06.handler;

import com.vinsguru.playground.sec06.dto.CustomerDto;
import com.vinsguru.playground.sec06.exceptions.ApplicationExceptions;
import com.vinsguru.playground.sec06.entity.Customer;
import com.vinsguru.playground.sec06.service.CustomerService;
import com.vinsguru.playground.sec06.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CustomerRequestHandler {

    private final CustomerService customerService;

    public Mono<ServerResponse> allCustomers(ServerRequest request) {
        return customerService.getAllCustomers()
                .as(flux -> ServerResponse.ok()
                        .body(flux, Customer.class));
    }

    public Mono<ServerResponse> getCustomerById(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariables().get("id"));
        return customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        return request.bodyToMono(CustomerDto.class)
                .as(customerService::saveCustomer)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> updateCustomer(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariables().get("id"));
        return request.bodyToMono(CustomerDto.class)
                .as(customer -> customerService.updateCustomer(id, customer))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariables().get("id"));
        return customerService.deleteCustomerById(id)
                .filter(b -> b)
                .then(ServerResponse.ok().build());
    }
}
