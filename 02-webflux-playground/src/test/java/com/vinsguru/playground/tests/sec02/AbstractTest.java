package com.vinsguru.playground.tests.sec02;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "sec=sec02",
        "spring.sql.init.data-locations=classpath:sql/data.sql"
})
public abstract class AbstractTest {
}
