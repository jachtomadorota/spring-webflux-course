package com.vinsguru.playground.sec01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final RestClient restClient = RestClient.builder().build();
    private final WebClient webClient = WebClient.builder().build();


    @GetMapping("/tradiional")
    public List<Product> getProducts() {
        log.info("Received response.");
        return restClient.get()
                .uri(URI.create("http://localhost:7070/demo01/products"))
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>(){});
    }

    @GetMapping(value = "/reactive")
    public Flux<Product> getProductsReactive() {
        log.info("Received response.");
        return webClient.get()
                .uri(URI.create("http://localhost:7070/demo01/products"))
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @GetMapping(value = "/reactive-stream", produces =  MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProductsStream() {
        log.info("Received response.");
        return webClient.get()
                .uri(URI.create("http://localhost:7070/demo01/products"))
                .retrieve()
                .bodyToFlux(Product.class);
    }
}
