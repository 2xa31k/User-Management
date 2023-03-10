package com.management.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.management.user.entity.Skill;

import reactor.core.publisher.Flux;

@Repository
public interface SkillRepository extends ReactiveCrudRepository<Skill, Long>{

	Flux<Skill> findAllByUserId(Long id);
}
