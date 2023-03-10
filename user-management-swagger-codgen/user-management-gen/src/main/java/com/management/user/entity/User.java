package com.management.user.entity;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("users")
public class User {

	@Id
	private Long id;
	private String firstname;
	private String lastname;
	@Pattern(regexp = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$")
	private String email;
	private String phonenumber;
	private Long salaire;
}
