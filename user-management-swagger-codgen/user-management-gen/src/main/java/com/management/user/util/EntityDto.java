package com.management.user.util;

import org.springframework.beans.BeanUtils;

import com.management.user.dto.UserDTO;
import com.management.user.entity.User;

public class EntityDto {

	public static UserDTO toDto(User user){
		UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
	
	public static User toEntity(UserDTO dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
