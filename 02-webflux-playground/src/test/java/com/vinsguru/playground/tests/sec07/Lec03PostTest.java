package com.vinsguru.playground.tests.sec07;

import com.vinsguru.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Lec03PostTest extends AbstractWebClient {

    private final WebClient webClient = this.createWebClient();


    @Test
    public void postTest() {
        webClient.post()
                .uri("/lec02/product")
                //.body(Mono.just(new Product(11, "Product description", 340)))
                .retrieve()
                .bodyToMono(Product.class)
                .doOnError(print())
                .subscribe();
    }
}
