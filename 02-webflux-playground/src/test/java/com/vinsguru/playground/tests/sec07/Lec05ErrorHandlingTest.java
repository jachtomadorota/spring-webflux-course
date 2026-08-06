package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.CalculatorResponse;
import com.vinsguru.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec05ErrorHandlingTest extends AbstractWebClient {

    private final WebClient webClient = this.createWebClient();


    @Test
    public void errorHandlingHappyPathTest() {
        webClient.get()
                .uri("/lec05/calculator/{first}/{second}", 10, 20)
                .header("operation", "+")
                .retrieve()
                .bodyToMono(CalculatorResponse.class)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    public void errorHandlingNegativePathTest() {
        webClient.get()
                .uri("/lec06/calculator/{first}/{second}", 10, 20)
                .retrieve()
                .bodyToMono(CalculatorResponse.class)
                .doOnError(print())
                .onErrorReturn(new CalculatorResponse(0, 0, null, 0.0))
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
