package com.vinsguru.playground.sec02.repository;

import com.vinsguru.playground.sec02.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
