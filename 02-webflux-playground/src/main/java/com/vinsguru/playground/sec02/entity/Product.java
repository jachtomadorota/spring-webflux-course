package com.vinsguru.playground.sec02.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @Column(value = "ID")
    private Integer id;

    @Column(value = "DESCRIPTION")
    private String description;

    @Column(value = "PRICE")
    private Integer price;
}
