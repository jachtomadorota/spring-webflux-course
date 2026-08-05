package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.CalculatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class Lec06ExchangeTest extends AbstractWebClient {

    private final WebClient webClient = this.createWebClient(builder -> builder.defaultHeader("caller-id", "test-service"));


    @Test
    public void errorHandlingHappyPathTest() {
        webClient.get()
                .uri("/lec05/calculator/{first}/{second}", 10, 20)
                .header("operation", "+")
                .exchangeToMono(this::decode)
                .doOnError(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    private Mono<CalculatorResponse> decode(ClientResponse clientResponse) {
        log.info("status code: {}", clientResponse.statusCode());
        var statusCode = clientResponse.statusCode();
        if(statusCode.is4xxClientError()) {
            return clientResponse.bodyToMono(ProblemDetail.class)
                    .then(Mono.empty());
        }
        return clientResponse.bodyToMono(CalculatorResponse.class);
    }
}
