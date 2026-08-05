package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec07QueryParametersTest extends AbstractWebClient {

    private final WebClient webClient = this.createWebClient(builder -> builder.defaultHeader("caller-id", "test-service"));

    @Test
    public void uriBuilderVariablesTest() {
        var path = "/lec06/calculator";
        var query = "first={first}&second={second}&operation={operation}";
        webClient.get()
                .uri(builder -> builder.path(path).query(query).build(10, 20, "+"))
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
