package com.vinsguru.playground.sec06.config;

import com.vinsguru.playground.sec06.exceptions.InvalidInputException;
import com.vinsguru.playground.sec06.exceptions.CustomerNotFoundException;
import com.vinsguru.playground.sec06.handler.CustomerRequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterConfiguration {

    private final CustomerRequestHandler handler;
    private final ApplicationExceptionHandler exceptionHandler;

    @Bean
    public RouterFunction<ServerResponse> customerRoutes() {
        return RouterFunctions.route()
                .GET("/customers", handler::allCustomers)
                .GET("/customers/{id}", handler::getCustomerById)
                .POST("/customers", handler::saveCustomer)
                .PUT("/customers/{id}", handler::updateCustomer)
                .DELETE("/customers/{id}", handler::deleteCustomer)
                .onError(CustomerNotFoundException.class, exceptionHandler::handleException)
                .onError(InvalidInputException.class, exceptionHandler::handleException)
                .build();
    }

}
