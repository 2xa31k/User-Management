package com.management.user;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.management.user.Entity.User;
import com.management.user.Repository.UserRepository;
import com.management.user.Service.UserService;
import com.management.user.Util.EntityDto;
import com.management.user.dto.UserDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class UserManagementServiceTest {

	@MockBean
	UserRepository uRep;
	@Autowired
	UserService uServ;
	
	@Test
	void getAll()
	{
		when(uRep.findAll()).thenReturn(Flux.just(new User(),new User()));
		Flux<UserDto> userFlux = uServ.getAll();
        StepVerifier
                .create(userFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
	
	@Test
	void getById()
	{
		when(uRep.findById(1L)).thenReturn(Mono.just(getUserEntity()));
		Mono<UserDto> userFlux = uServ.getById(1L);
        StepVerifier
                .create(userFlux)
                .consumeNextWith(i -> 
                Assertions.assertEquals(i.getEmail(), "aelk@gmail.com"))
                .verifyComplete();
    }
	
	@Test
	void getByIdNotExiste()
	{
		when(uRep.findById(1L)).thenReturn(Mono.empty());
		Mono<UserDto> userFlux = uServ.getById(1L);
        StepVerifier
                .create(userFlux)
                .expectNextCount(0)
                .verifyComplete();
    }
	
	@Test
	void addUser()
	{
		when(uRep.save(Mockito.any())).thenReturn(Mono.just(getUserEntity()));
		
		Mono<UserDto> userFlux = uServ.addUser(Mono.just(getUserDto()));
        StepVerifier
                .create(userFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
	
	@Test
	void updateUser()
	{
		when(uRep.findById(1L)).thenReturn(Mono.just(getUserEntity()));
		when(uRep.save(Mockito.any())).thenReturn(Mono.just(getUserEntity()));
		
		Mono<UserDto> userFlux = uServ.updateUser(1L,Mono.just(getUserDto()));
        StepVerifier
                .create(userFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
	
	@Test
	void updateUserNotExiste()
	{
		when(uRep.findById(1L)).thenReturn(Mono.empty());
		
		Mono<UserDto> userFlux = uServ.updateUser(1L,Mono.just(getUserDto()));
        StepVerifier
                .create(userFlux)
                .expectNextCount(0)
                .verifyComplete();
    }
    
	@Test
	void deleteUser()
	{
		when(uRep.deleteById(1L)).thenReturn(Mono.empty());
		
		Mono<Void> userFlux = uServ.deleteUser(1L);
        StepVerifier
                .create(userFlux)
                .verifyComplete();
    }
	
	
	public static  User getUserEntity()
	{
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000",5000L);
		return EntityDto.toEntity(user1);
	}
	
	public static  UserDto getUserDto()
	{
		UserDto user1 = new UserDto(10L,"ayoub","elk","aelk@gmail.com","+212600000",5000L);
		return user1;
	}
	
	}

