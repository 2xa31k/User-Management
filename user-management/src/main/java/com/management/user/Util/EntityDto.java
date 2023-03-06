package com.management.user.Util;

import org.springframework.beans.BeanUtils;

import com.management.user.Entity.User;
import com.management.user.dto.UserDto;

public class EntityDto {

	public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
	
	public static User toEntity(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
