package com.vinsguru.playground.tests.sec02;

import com.vinsguru.playground.sec02.dto.OrderDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

@Slf4j
public class Lec04DatabaseClientTest extends AbstractTest {

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    public void orderDetailsByProductTest() {
        var query = """
                SELECT
                co.order_id,
                c.name AS customer_name,
                p.description AS product_name,
                co.amount,
                co.order_date
            FROM
                customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON p.id = co.product_id
            WHERE
                p.description = :description
            ORDER BY co.amount DESC
            """;

        this.databaseClient.sql(query)
                .bind("description", "iphone 20")
                .mapProperties(OrderDetails.class)
                .all()
                .doOnNext(s -> log.info("{}", s))
                .as(StepVerifier::create)
                .expectNextCount(0)
                .expectComplete()
                .verify();

    }
}
