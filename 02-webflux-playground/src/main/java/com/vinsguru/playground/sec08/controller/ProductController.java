package com.vinsguru.playground.sec08.controller;

import com.vinsguru.playground.sec08.dto.ProductDto;
import com.vinsguru.playground.sec08.dto.UploadResponse;
import com.vinsguru.playground.sec08.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    private final ProductService productService;

    @PostMapping(value = "upload", consumes = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<UploadResponse> uploadProducts(@RequestBody Flux<ProductDto> flux) {
        log.info("invoked");
        return productService.saveProducts(flux.doOnNext(s -> log.info("receive incoming data")))
                .then(productService.getCount())
                .map(count -> new UploadResponse(UUID.randomUUID(), count));
    }



}
