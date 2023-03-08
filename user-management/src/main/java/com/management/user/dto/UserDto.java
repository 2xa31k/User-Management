package com.management.user.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends RepresentationModel<UserDto>
implements Serializable{
	
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;
	private Long salaire;
	
	
	
}
