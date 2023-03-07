package com.management.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.user.Service.UserService;
import com.management.user.dto.UserDto;
import com.management.user.handling.UserNotFound;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServ;
	
	@GetMapping("/")
	public Flux<UserDto> getAll()
	{
		return this.userServ.getAll();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<UserDto>> getById(@PathVariable Long id)
	{

		 return this.userServ.getById(id)
		 .switchIfEmpty(Mono.error(new UserNotFound("user with "+id+" not found")))
		 .map(ResponseEntity::ok);
				

	}
	
	@PostMapping("/")
	public Mono<ResponseEntity<UserDto>> addUser(@RequestBody Mono<UserDto> dto)
	{
		return this.userServ.addUser(dto)
				.map(ResponseEntity::ok);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable Long id,@RequestBody Mono<UserDto> dto)
	{
		return this.userServ.updateUser(id,dto)
				.switchIfEmpty(Mono.error(new UserNotFound("user with id "+id+" not found")))
				.map(ResponseEntity::ok);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable Long id)
	{
		return this.userServ.deleteUser(id);
	}
}
