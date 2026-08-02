package com.vinsguru.playground.tests.sec02;

import com.vinsguru.playground.sec02.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import reactor.test.StepVerifier;

public class Lec02ProductRepositoryTest extends AbstractTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void findFromPriceToPriceTest() {

        this.repository.findByPriceBetween(100, 500)
                .as(StepVerifier::create)
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }

    @Test
    public void findAllWithPageableTest() {

        this.repository.findAllBy(Pageable.ofSize(3).withPage(1))
                .as(StepVerifier::create)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }
}
