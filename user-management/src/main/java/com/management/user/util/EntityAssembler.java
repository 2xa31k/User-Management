package com.management.user.util;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.management.user.controller.UserController;
import com.management.user.dto.UserDto;
import com.management.user.entity.User;

@Component
public class EntityAssembler extends RepresentationModelAssemblerSupport<User, UserDto> {

	public EntityAssembler() {
		super(UserController.class, UserDto.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDto toModel(User entity) {
		UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
}
