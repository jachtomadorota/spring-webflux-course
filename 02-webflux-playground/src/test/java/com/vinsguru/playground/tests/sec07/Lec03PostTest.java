package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec03PostTest extends AbstractWebClient {

    private final WebClient webClient = this.createWebClient();


    @Test
    public void postTestBodyValue() {
        webClient.post()
                .uri("/lec03/product")
                .bodyValue(new Product(11, "Product description", 340))
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    public void postTestBody() {
        webClient.post()
                .uri("/lec03/product")
                .body(Mono.just(new Product(11, "Product description", 340)), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
