package com.management.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.management.user.api.SkillsApi;
import com.management.user.dto.SkillDTO;
import com.management.user.service.SkillService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SkillController implements SkillsApi {

	@Autowired
	private SkillService skillServ;
	@Override
	public Mono<ResponseEntity<Flux<SkillDTO>>> skillsIdGet(Long id) {
		return Mono.just(skillServ.getAll(id))
				.map(ResponseEntity::ok);
	}

	@Override
	public Mono<ResponseEntity<SkillDTO>> skillsIdPost(Long id, @Valid SkillDTO skillDTO) {
		return skillServ.addSkill(id, Mono.just(skillDTO))
				.map(ResponseEntity::ok);
	}

	
}
