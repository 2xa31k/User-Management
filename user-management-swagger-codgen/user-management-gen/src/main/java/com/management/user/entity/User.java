package com.management.user.entity;

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
	private String email;
	private String phonenumber;
	private Long salaire;
}
