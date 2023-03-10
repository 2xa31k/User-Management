package com.management.user.dto;

import java.util.Optional;

import org.springframework.hateoas.PagedModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse {

	PagedModel<UserDto> users;
	Long totalSalaire;
	Long averageSalaire;
}
