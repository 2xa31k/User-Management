package com.management.user.util;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.management.user.controller.UserController;
import com.management.user.dto.UserDTO;
import com.management.user.entity.User;

@Component
public class EntityAssembler extends RepresentationModelAssemblerSupport<User, UserDTO> {

	public EntityAssembler() {
		super(UserController.class, UserDTO.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDTO toModel(User entity) {
		UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
}
