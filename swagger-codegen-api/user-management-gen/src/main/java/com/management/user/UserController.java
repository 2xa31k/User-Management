package com.management.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.client.api.UserControllerApi;
import com.user.management.client.model.UserDto;

@RestController
public class UserController {
	
	@Autowired
    private UserControllerApi userApi;
	
	@GetMapping("/all")
	public Object allUsers() {
		return userApi.getAllUsingGET();
	}
	
	@GetMapping("/{id}")
	public Object getById(@PathVariable Long id) {
		return userApi.getByIdUsingGET(id);
	}
	
	@PostMapping("/add")
	public Object addUser(@RequestBody UserDto dto) {
		return userApi.addUserUsingPOST(dto);
	}
	
	@PutMapping("/{id}")
	public Object updateUser(@PathVariable Long id,@RequestBody UserDto dto) {
		return userApi.updateUserUsingPUT(dto,id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		 userApi.deleteUserUsingDELETE(id);
	}
	
}
