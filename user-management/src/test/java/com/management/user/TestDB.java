package com.management.user;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.TestPropertySource;

import com.management.user.Entity.User;
import com.management.user.Repository.UserRepository;
import com.management.user.Service.UserService;
import com.management.user.dto.UserDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class TestDB {
	
	@Autowired
	UserService uServ;
	@Autowired
	UserRepository uRep;
	@Autowired 
	DatabaseClient database;
	
	@BeforeEach
	void setUp() {


		var statements = Arrays.asList(//
				"DROP TABLE IF EXISTS users;",
				"CREATE TABLE users ( id SERIAL4 PRIMARY KEY, firstname VARCHAR(100) NOT NULL, lastname VARCHAR(100) NOT NULL,"
				+ "email VARCHAR(100) NOT NULL,phonenumber VARCHAR(100) NOT NULL,salaire int8);",
				"INSERT INTO public.users (firstname,lastname,email,phonenumber,salaire) VALUES\r\n"
				+ "	 ('Ayoub','Elk','aelk@test.com','0600000',3000)",
				"INSERT INTO public.users (firstname,lastname,email,phonenumber,salaire) VALUES\r\n"
				+ "	 ('John','Snow','john@test.com','0900000',4000)",
				"INSERT INTO public.users (firstname,lastname,email,phonenumber,salaire) VALUES\r\n"
				+ "	 ('sam','bass','sam@test.com','0500000',5000)");

		statements.forEach(it -> database.sql(it) 
				.fetch() 
				.rowsUpdated() 
				.as(StepVerifier::create) 
				.expectNextCount(1) 
				.verifyComplete());
	}
	
	@Test
	void getAll()
	{
		Flux<UserDto> userFlux = uServ.getAll();
			StepVerifier
            .create(userFlux)
            .expectNextCount(3)
            .verifyComplete();
	}
	
	@Test
	void getById()
	{
		UserDto user = uServ.getById(1L).block();
		Assertions.assertEquals(user.getId(), 1L);
		Assertions.assertEquals(user.getEmail(), "aelk@test.com");
	}
	
	@Test
	void getByIdNotExiste()
	{
		UserDto user = uServ.getById(5L).block();
		Assertions.assertNull(user);
	}
	
	
	@Test
	void addUser()
	{
		UserDto userDto = uServ.getById(4L).block();
		Assertions.assertNull(userDto);
		
		uServ.addUser(getUserDto()).block();
		
		User user = uRep.findById(4L).block();
		Assertions.assertNotNull(user);
	}
	
	@Test
	void update()
	{
		UserDto userDto = uServ.getById(1L).block();
		Assertions.assertEquals(userDto.getEmail(), "aelk@test.com");
		
		uServ.updateUser(1L, getUserDto()).block();
		
		User user = uRep.findById(1L).block();
		Assertions.assertEquals(user.getEmail(), "test@test.com");
	}
	
	@Test
	void updateNotExiste()
	{
		UserDto user = uServ.getById(10L).block();
		Assertions.assertNull(user);
		
		user = uServ.updateUser(10L, getUserDto()).block();
		Assertions.assertNull(user);
	}
	
	@Test
	void delete()
	{
		UserDto userDto = uServ.getById(1L).block();
		Assertions.assertNotNull(userDto);
		
		uServ.deleteUser(1L).block();
		User user = uRep.findById(1L).block();
		Assertions.assertNull(user);
	}
	
	
	public static  Mono<UserDto> getUserDto()
	{
		Mono<UserDto> mono = Mono.just(UserDto.builder().email("test@test.com").firstname("test").lastname("test").phonenumber("06000").salaire(5000L).build());
		return mono;
	}
}
