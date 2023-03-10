package com.management.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.management.user.entity.User;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long>{

	Flux<User> findAllBySalaireBetween(Integer minSalaire, Integer maxSalaire,Pageable pageable);
}
