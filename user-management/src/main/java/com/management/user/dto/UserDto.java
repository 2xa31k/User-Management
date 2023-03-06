package com.management.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;
	
	public UserDto(String firstname, String lastname, String email, String phonenumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
	}
	
	
}
