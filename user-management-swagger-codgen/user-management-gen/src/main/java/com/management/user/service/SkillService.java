package com.management.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.user.dto.SkillDTO;
import com.management.user.repository.SkillRepository;
import com.management.user.util.EntityDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRep;
	
	
	public Flux<SkillDTO> getAll(Long id){
		
		return skillRep.findAllByUserId(id)
				.map(EntityDto::toDto);
	}
	

	public Mono<SkillDTO> addSkill(Long id,Mono<SkillDTO> dto){
		return dto.map(s -> EntityDto.toEntity(id, s))
				.flatMap(this.skillRep::save)
				.map(EntityDto::toDto);
	}
	
	
}
