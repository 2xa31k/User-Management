package com.management.user.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.user.Entity.User;
import com.management.user.Service.UserService;
import com.management.user.Util.EntityAssembler;
import com.management.user.dto.UserDto;
import com.management.user.handling.UserNotFound;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServ;




	@Autowired
	private EntityAssembler entityAssembler;

	@Autowired
	private PagedResourcesAssembler<User> pagedResourcesAssembler;

	
	
	@GetMapping("/")
	public Flux<UserDto> getAll()
	{
		 return this.userServ.getAll()
		 	.map(d ->{
		 		d.add(linkTo(UserController.class).slash(d.getId()).withSelfRel());
		 		d.add(linkTo(methodOn(UserController.class).getAll()).withRel("person"));
		 		
		 	return d;});
		 	
		 }
				

	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<UserDto>> getById(@PathVariable Long id)
	{
		Link link = linkTo(methodOn(UserController.class)
			      .getById(id)).withSelfRel();
		 return this.userServ.getById(id)
		 .switchIfEmpty(Mono.error(new UserNotFound("user with "+id+" not found")))
		 .map(d-> d.add(link))
		 .map(ResponseEntity::ok);
				

	}
	
	@PostMapping("/")
	public Mono<ResponseEntity<UserDto>> addUser(@RequestBody UserDto dto)
	{
		Link link = linkTo(methodOn(UserController.class)
			      .addUser(dto)).withSelfRel();
		return this.userServ.addUser(Mono.just(dto))
				.map(d-> d.add(link))
				.map(ResponseEntity::ok);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable Long id,@RequestBody UserDto dto)
	{
		Link link = linkTo(methodOn(UserController.class)
			      .updateUser(id,dto)).withSelfRel();
		return this.userServ.updateUser(id,Mono.just(dto))
				.switchIfEmpty(Mono.error(new UserNotFound("user with id "+id+" not found")))
				.map(d-> d.add(link))
				.map(ResponseEntity::ok);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable Long id)
	{
		return this.userServ.deleteUser(id);
	}
}
