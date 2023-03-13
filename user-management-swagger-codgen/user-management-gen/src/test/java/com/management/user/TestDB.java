package com.management.user;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.TestPropertySource;

import com.management.user.dto.UserDTO;
import com.management.user.entity.User;
import com.management.user.repository.UserRepository;
import com.management.user.service.UserService;

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
				"DROP TABLE IF EXISTS skills;",
				"CREATE TABLE users ( id SERIAL4 PRIMARY KEY, firstname VARCHAR(100) NOT NULL, lastname VARCHAR(100) NOT NULL,"
				+ "email VARCHAR(100) NOT NULL,phonenumber VARCHAR(100) NOT NULL,salaire int8);",
				"CREATE TABLE skills ( id SERIAL4 PRIMARY KEY, user_id int8, name VARCHAR(100) NOT NULL,"
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
		Flux<UserDTO> userFlux = uServ.getAll();
			StepVerifier
            .create(userFlux)
            .expectNextCount(3)
            .verifyComplete();
	}
	
	@Test
	void getById()
	{
		UserDTO user = uServ.getById(1L).block();
		Assertions.assertEquals(user.getId(), 1L);
		Assertions.assertEquals(user.getEmail(), "aelk@test.com");
	}
	
	@Test
	void getByIdNotExiste()
	{
		UserDTO user = uServ.getById(5L).block();
		Assertions.assertNull(user);
	}
	
	
	@Test
	void addUser()
	{
		UserDTO UserDTO = uServ.getById(4L).block();
		Assertions.assertNull(UserDTO);
		
		uServ.addUser(getUserDTO()).block();
		
		User user = uRep.findById(4L).block();
		Assertions.assertNotNull(user);
	}
	
	@Test
	void update()
	{
		UserDTO UserDTO = uServ.getById(1L).block();
		Assertions.assertEquals(UserDTO.getEmail(), "aelk@test.com");
		
		uServ.updateUser(1L, getUserDTO()).block();
		
		User user = uRep.findById(1L).block();
		Assertions.assertEquals(user.getEmail(), "test@test.com");
	}
	
	@Test
	void updateNotExiste()
	{
		UserDTO user = uServ.getById(10L).block();
		Assertions.assertNull(user);
		
		user = uServ.updateUser(10L, getUserDTO()).block();
		Assertions.assertNull(user);
	}
	
	@Test
	void delete()
	{
		UserDTO UserDTO = uServ.getById(1L).block();
		Assertions.assertNotNull(UserDTO);
		
		uServ.deleteUser(1L).block();
		User user = uRep.findById(1L).block();
		Assertions.assertNull(user);
	}
	
	
	public static  Mono<UserDTO> getUserDTO()
	{
		Mono<UserDTO> mono = Mono.just(UserDTO.builder().email("test@test.com").firstname("test").lastname("test").phonenumber("06000").salaire(5000L).build());
		return mono;
	}
}
