package com.vinsguru.playground.tests.sec02;

import com.vinsguru.playground.sec02.entity.Customer;
import com.vinsguru.playground.sec02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

public class Lec01CustomerRepositoryTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec01CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository repository;


    @Test
    public void findAllTest() {
        this.repository.findAll()
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }

    @Test
    public void findByIdTest() {
        this.repository.findById(2)
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByNameTest() {
        this.repository.findByName("jake")
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .assertNext(c -> Assertions.assertEquals("jake", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByEmailTest() {
        this.repository.findByEmail("emily@example.com")
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .assertNext(c -> Assertions.assertEquals("emily", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void insertAndDeleteCustomerTest() {
        var customer = new Customer();
        customer.setName("Dorota");
        customer.setEmail("dorota@meial.com");
        this.repository.save(customer);

        this.repository.findByEmail("dorota@meial.com")
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .assertNext(c -> Assertions.assertEquals("Dorota", c.getName()))
                .expectComplete()
                .verify();

        this.repository.delete(customer);

        this.repository.findByEmail("dorota@meial.com")
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

}
