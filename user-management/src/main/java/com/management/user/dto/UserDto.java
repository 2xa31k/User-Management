package com.management.user.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;
}
