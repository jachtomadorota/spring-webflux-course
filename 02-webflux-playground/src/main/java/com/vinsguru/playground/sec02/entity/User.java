package com.vinsguru.playground.sec02.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "customer")
public class User {

    @Id
    Integer id;
    String name;
    String email;

    public User(String name, Integer id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public User() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
