package com.management.user.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.management.user.Entity.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long>{

}
