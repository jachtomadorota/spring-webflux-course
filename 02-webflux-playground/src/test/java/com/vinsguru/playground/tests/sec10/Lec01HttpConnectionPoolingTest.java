package com.vinsguru.playground.tests.sec10;

import com.vinsguru.playground.tests.sec10.dto.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.test.StepVerifier;

public class Lec01HttpConnectionPoolingTest extends AbstractWebClient {

    private final WebClient client = this.createWebClient(b -> {
        var poolSize = 500;
        var provider = ConnectionProvider.builder("webClientTest")
                .lifo()
                .maxConnections(poolSize)
                .pendingAcquireMaxCount(poolSize * 5)
                .build();
        var httpClient = HttpClient.create(provider)
                .compress(true)
                .keepAlive(true);
        b.clientConnector(new ReactorClientHttpConnector(httpClient));
    });

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

    private Mono<Product> getProduct(int i) {
        return this.client.get()
                .uri("/product/{id}", i)
                .retrieve().bodyToMono(Product.class);
    }
}
