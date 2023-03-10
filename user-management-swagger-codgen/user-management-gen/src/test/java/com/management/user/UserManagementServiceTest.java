package com.management.user;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.management.user.dto.UserDTO;
import com.management.user.entity.User;
import com.management.user.repository.UserRepository;
import com.management.user.service.UserService;
import com.management.user.util.EntityDto;

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
		Flux<UserDTO> userFlux = uServ.getAll();
        StepVerifier
                .create(userFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
	
	@Test
	void getById()
	{
		when(uRep.findById(1L)).thenReturn(Mono.just(getUserEntity()));
		Mono<UserDTO> userFlux = uServ.getById(1L);
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
		Mono<UserDTO> userFlux = uServ.getById(1L);
        StepVerifier
                .create(userFlux)
                .expectNextCount(0)
                .verifyComplete();
    }
	
	@Test
	void addUser()
	{
		when(uRep.save(Mockito.any())).thenReturn(Mono.just(getUserEntity()));
		
		Mono<UserDTO> userFlux = uServ.addUser(Mono.just(getUserDTO()));
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
		
		Mono<UserDTO> userFlux = uServ.updateUser(1L,Mono.just(getUserDTO()));
        StepVerifier
                .create(userFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
	
	@Test
	void updateUserNotExiste()
	{
		when(uRep.findById(1L)).thenReturn(Mono.empty());
		
		Mono<UserDTO> userFlux = uServ.updateUser(1L,Mono.just(getUserDTO()));
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
		UserDTO user1 = new UserDTO(10L,"ayoub","elk","aelk@gmail.com","+212600000",5000L,null);
		return EntityDto.toEntity(user1);
	}
	
	public static  UserDTO getUserDTO()
	{
		UserDTO user1 = new UserDTO(10L,"ayoub","elk","aelk@gmail.com","+212600000",5000L,null);
		return user1;
	}
	
	}

