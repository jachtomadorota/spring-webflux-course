package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec08BearerAuthTest extends AbstractWebClient {


    private final WebClient webClient = this.createWebClient();

    @Test
    public void uriBuilderVariablesTest() {
        webClient.get()
                .uri("/lec08/product/{id}", 1)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
