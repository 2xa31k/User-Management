package com.management.user;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.management.user.Service.UserService;
import com.management.user.dto.UserDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
class UserManagementControllerTests {

	@MockBean
	UserService userServ;
	
    @Autowired
    private WebTestClient client;
    
	@Test
	void getAllUser(){
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000");
		UserDto user2 = new UserDto(12L,"test","test","test.com","+212600000");
		when(userServ.getAll()).thenReturn(Flux.just(user1,user2));
		
		Flux<UserDto> response = this.client
        .get()
        .uri("/user/")
        .exchange()
        .expectStatus().is2xxSuccessful()
        .returnResult(UserDto.class)
        .getResponseBody();
		
		StepVerifier.create(response)
		.expectNextCount(2)
		.verifyComplete();
	}
	
	@Test
	void getUserById() {
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000");
		when(userServ.getById(10L)).thenReturn(Mono.just(user1));
		
		Mono<UserDto> response = this.client
		        .get()
		        .uri("/user/{id}",10L)	
		        .exchange()
		        .expectStatus().is2xxSuccessful()
		        .returnResult(UserDto.class)
		        .getResponseBody()
		        .next();

				
				StepVerifier.create(response)
				.expectNextCount(1)
				.verifyComplete();
			}
	
	@Test
	void getUserByIdNotExiste() {
		when(userServ.getById(10L)).thenReturn(Mono.empty());
		
		Mono<UserDto> response = this.client
		        .get()
		        .uri("/user/{id}",10L)	
		        .exchange()
		        .expectStatus().is4xxClientError()
		        .returnResult(UserDto.class)
		        .getResponseBody()
		        .next();

				
				StepVerifier.create(response)
				.expectNextCount(0)
				.verifyComplete();
			}
	
	@Test
	void addUser() {
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000");
		when(userServ.addUser(Mockito.any())).thenReturn(Mono.just(user1));
		
		Mono<UserDto> response = this.client
		        .post()
		        .uri("/user/")
                .accept(MediaType.APPLICATION_JSON)
		        .bodyValue(user1)
		        .exchange()
		        .expectStatus().is2xxSuccessful()
		        .returnResult(UserDto.class)
		        .getResponseBody()
		        .next();

				
				StepVerifier.create(response)
				.expectNextMatches(r -> r.getEmail().equals(user1.getEmail()))
				.verifyComplete();
			}
	
	@Test
	void update() {
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000");
		when(userServ.updateUser(Mockito.any(),Mockito.any())).thenReturn(Mono.just(user1));
		
		Mono<UserDto> response = this.client
		        .put()
		        .uri("/user/{id}",10L)
                .accept(MediaType.APPLICATION_JSON)
		        .bodyValue(user1)
		        .exchange()
		        .expectStatus().is2xxSuccessful()
		        .returnResult(UserDto.class)
		        .getResponseBody()
		        .next();

				
				StepVerifier.create(response)
				.expectNextMatches(r -> r.getEmail().equals(user1.getEmail()))
				.verifyComplete();
			}
	
	@Test
	void updateIdNotExiste() {
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000");
		when(userServ.updateUser(Mockito.any(),Mockito.any())).thenReturn(Mono.empty());
		
		Mono<UserDto> response = this.client
		        .put()
		        .uri("/user/{id}",1)
                .accept(MediaType.APPLICATION_JSON)
		        .bodyValue(user1)
		        .exchange()
		        .expectStatus().is4xxClientError()
		        .returnResult(UserDto.class)
		        .getResponseBody()
		        .next();

				
				StepVerifier.create(response)
				.expectNextCount(0)
				.verifyComplete();
			}
	
	@Test
	void deleteUser() {
		
		
		when(userServ.deleteUser(1L)).thenReturn(Mono.empty());
		
		Mono<Void> response = this.client
		        .delete()
		        .uri("/user/{id}",1L)
                .accept(MediaType.APPLICATION_JSON)
		        .exchange()
		        .expectStatus().is2xxSuccessful()
		        .returnResult(Void.class)
		        .getResponseBody()
		        .next()
		        ;

				
				StepVerifier.create(response)
				.verifyComplete();
			}
	}
	

