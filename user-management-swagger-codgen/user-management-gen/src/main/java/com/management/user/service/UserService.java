package com.management.user.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.management.user.controller.UserController;
import com.management.user.dto.UserDTO;
import com.management.user.entity.User;
import com.management.user.repository.SkillRepository;
import com.management.user.repository.UserRepository;
import com.management.user.util.EntityAssembler;
import com.management.user.util.EntityDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class UserService {

	@Autowired
	UserRepository userRep;
	@Autowired
	SkillRepository skillRep;
	@Autowired
	private EntityAssembler entityAssembler;
	@Autowired
	private PagedResourcesAssembler<User> pagedResourcesAssembler;
	
	public Flux<UserDTO> getAll(){
		
		return userRep.findAll()
				.map(EntityDto::toDto);
	}
	
	public Mono<UserDTO> getById(Long id){
		   
		return this.userRep
				.findById(id)
				.map(EntityDto::toDto)
				.flatMap(u ->
				this.skillRep.findAllByUserId(id).map(EntityDto::toDto).collectList().flatMap(s -> {
					u.setSkills(s);
					return Mono.just(u);
				})
				);
	}
	
	public Mono<UserDTO> addUser(Mono<UserDTO> dto){
		return dto.map(EntityDto::toEntity)
				.flatMap(this.userRep::save)
				.map(EntityDto::toDto);
	}
	
	public Mono<UserDTO> updateUser(Long id,Mono<UserDTO> dto){
		return this.userRep
				.findById(id)
				.flatMap( u -> dto.map(EntityDto::toEntity)
								.doOnNext(e->e.setId(id)))
				.flatMap(this.userRep::save)
				.map(EntityDto::toDto);

	}
	
	public Mono<Void> deleteUser(Long id){
        return this.userRep.deleteById(id);
    }
	
	
	public Mono<PagedModel<UserDTO>> getAllUsers(Integer minSalaire, Integer maxSalaire, PageRequest pageRequest) {
	    Link link = linkTo(methodOn(UserController.class).usersSearchGet(pageRequest.getPageNumber(), pageRequest.getPageSize(), minSalaire, maxSalaire)).withRel("self");
	    return this.userRep.findAllBySalaireBetween(minSalaire, maxSalaire, pageRequest)
	            .collectList()
	            .zipWith(this.userRep.count())
	            .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()))
	            .flatMap(u -> Mono.just(pagedResourcesAssembler.toModel(u, entityAssembler, link)))
	            .flatMap(u -> {
	            	
	                Flux<Mono<UserDTO>> fromStream = Flux.fromStream(()->u.getContent().stream()
	                        .map(d -> this.skillRep.findAllByUserId(d.getId())
	                                .map(EntityDto::toDto)
	                                .collectList()
	                                .flatMap(skills -> {
	                                	
	                                    d.setSkills(skills);
	                                    d.add(linkTo(methodOn(UserController.class).usersIdGet(d.getId())).withRel("self"));
	                                    return Mono.just(d);
	                                }).subscribeOn(Schedulers.boundedElastic())));
	                
	                return Flux.merge(fromStream).collectList()
	                		.flatMap(dtos -> Mono.just(PagedModel.of(dtos, u.getMetadata(),u.getLinks())));
	                	
	            });
	}
}

