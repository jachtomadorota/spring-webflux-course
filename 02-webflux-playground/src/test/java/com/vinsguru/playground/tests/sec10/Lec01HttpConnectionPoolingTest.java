package com.vinsguru.playground.tests.sec10;

import com.vinsguru.playground.tests.sec10.dto.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01HttpConnectionPoolingTest extends AbstractWebClient {

    private final WebClient client = this.createWebClient();

    @Test
    public void concurrentRequestTest() {
        var max = 1;
        Flux.range(1, max)
                .flatMap(this::getProduct)
                .collectList()
                .as(StepVerifier::create)
                .assertNext(product -> Assertions.assertEquals(max, product.size()))
                .expectComplete()
                .verify();
    }

    private Mono<Product> getProduct(int i ) {
        return this.client.get()
                .uri("/product/{id}", i)
                .retrieve().bodyToMono(Product.class);
    }
}
