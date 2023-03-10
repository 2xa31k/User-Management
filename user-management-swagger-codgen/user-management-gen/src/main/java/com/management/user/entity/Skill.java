package com.management.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("skills")
public class Skill {

	@Id
	private Long id;
	private Long userId;
	private String name;
}
