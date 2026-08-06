package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.util.UUID;

public class Lec09FilterFunctionTest extends AbstractWebClient {

    private final WebClient webClient = this.createWebClient(b -> b.filter(tokenGeneration()));

    @Test
    public void filterFunctionTest() {
        webClient.get()
                .uri("/lec09/product/{id}", 1)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    private ExchangeFilterFunction tokenGeneration() {
        return (request, next) -> {
            var token = UUID.randomUUID().toString().replace("-", "");
            var modified = ClientRequest.from(request).headers(h -> h.setBearerAuth(token)).build();
            return next.exchange(modified);
        };
    }
}
