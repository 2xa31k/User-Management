package com.management.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.management.user.api.UsersApi;
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
	public Mono<ResponseEntity<Flux<UserDTO>>> usersGet(ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		 return Mono.just(this.userServ.getAll())
				 .map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<Void>> usersIdDelete(Long id, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return this.userServ.deleteUser(id)
				.map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<UserDTO>> usersIdGet(Long id, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return this.userServ.getById(id)
				 .switchIfEmpty(Mono.error(new UserNotFound("user with "+id+" not found")))
				 .map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<UserDTO>> usersIdPut(Long id, Mono<UserDTO> userDTO, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return this.userServ.updateUser(id,userDTO)
				.switchIfEmpty(Mono.error(new UserNotFound("user with id "+id+" not found")))
				.map(ResponseEntity::ok);
	}



	@Override
	public Mono<ResponseEntity<UserDTO>> usersPost(@Valid Mono<UserDTO> userDTO, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return this.userServ.addUser(userDTO)
				.map(ResponseEntity::ok);
	}


}
