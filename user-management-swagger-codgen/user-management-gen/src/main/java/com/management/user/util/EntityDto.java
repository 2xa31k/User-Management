package com.management.user.util;

import org.springframework.beans.BeanUtils;

import com.management.user.dto.SkillDTO;
import com.management.user.dto.UserDTO;
import com.management.user.entity.Skill;
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
	
	public static SkillDTO toDto(Skill skill){
		SkillDTO dto = new SkillDTO();
        BeanUtils.copyProperties(skill, dto);
        return dto;
    }
	
	public static Skill toEntity(Long id,SkillDTO dto){
		Skill skill = new Skill();
		skill.setUserId(id);
		skill.setName(dto.getName());
        return skill;
    }
}
