package com.management.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.management.user.api.UsersApi;
import com.management.user.dto.PageResponseDTO;
import com.management.user.dto.UserDTO;
import com.management.user.handling.UserNotFound;
import com.management.user.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController implements UsersApi {

	@Autowired
	UserService userServ;

	
	


	@Override
	public Mono<ResponseEntity<PageResponseDTO>> usersSearchGet(Integer minSalaire, Integer maxSalaire,
			Integer page, Integer size) {
		// TODO Auto-generated method stub
		return  this.userServ.getAllUsers(minSalaire,maxSalaire,PageRequest.of(page, size))
        		
	       		 .flatMap(u -> {
	       			PageResponseDTO resp = PageResponseDTO.builder()
	       					 .users(u)
	       					 .averageSalaire(u.getContent().stream().map(d->d.getSalaire()).reduce(0L,Long::sum)/u.getContent().size())
	       					 .totalSalaire(u.getContent().stream().map(d->d.getSalaire()).reduce(0L,Long::sum))
	       					 .build();    			
	       			return Mono.just(resp);
	       		 })
	       			
	       		 .map(ResponseEntity::ok);
	}

	
	@Override
	public Mono<ResponseEntity<Flux<UserDTO>>> usersGet() {
		// TODO Auto-generated method stub
		 return Mono.just(this.userServ.getAll())
				 .map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<Void>> usersIdDelete(Long id) {
		// TODO Auto-generated method stub
		return this.userServ.deleteUser(id)
				.map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<UserDTO>> usersIdGet(Long id) {
		// TODO Auto-generated method stub
		return this.userServ.getById(id)
				 .switchIfEmpty(Mono.error(new UserNotFound("user with "+id+" not found")))
				 .map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<UserDTO>> usersIdPut(Long id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		return this.userServ.updateUser(id,Mono.just(userDTO))
				.switchIfEmpty(Mono.error(new UserNotFound("user with id "+id+" not found")))
				.map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<UserDTO>> usersPost(@Valid UserDTO userDTO) {
		// TODO Auto-generated method stub
		return this.userServ.addUser(Mono.just(userDTO))
				.map(ResponseEntity::ok);
	}


}
