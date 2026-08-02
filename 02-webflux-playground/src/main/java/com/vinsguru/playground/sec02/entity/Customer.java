package com.vinsguru.playground.sec02.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(value = "ID")
    Integer id;

    @Column(value = "NAME")
    String name;

    @Column(value = "EMAIL")
    String email;

}
