package com.vinsguru.playground.tests.sec02;

import com.vinsguru.playground.sec02.repository.CustomerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@Slf4j
public class Lec03CustomerOrderRepositoryTest extends AbstractTest {

    @Autowired
    private CustomerOrderRepository repository;

    @Test
    public void productsOrderedByCustomerTest() {
        this.repository.getProductsOrderedByCustomer("mike")
                .doOnNext(s -> log.info("{}", s))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();

    }

    @Test
    public void getOrderDetailsByProductTest() {
        this.repository.getOrderDetailsByProduct("iphone 20")
                .doOnNext(s -> log.info("{}", s))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();

    }
}
