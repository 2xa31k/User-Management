package com.management.user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.user.Repository.UserRepository;
import com.management.user.Util.EntityDto;
import com.management.user.dto.UserDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	UserRepository userRep;
	
	public Flux<UserDto> getAll(){
		
		return userRep.findAll()
				.map(EntityDto::toDto);
	}
	
	public Mono<UserDto> getById(Long id){
		   
		return this.userRep
				.findById(id)
				.map(EntityDto::toDto);
	}
	
	public Mono<UserDto> addUser(Mono<UserDto> dto){
		return dto.map(EntityDto::toEntity)
				.flatMap(this.userRep::save)
				.map(EntityDto::toDto);
	}
	
	public Mono<UserDto> updateUser(Long id,Mono<UserDto> dto){
		return this.userRep
				.findById(id)
				.flatMap( u -> dto.map(EntityDto::toEntity)
								.doOnNext(e->e.setId(id)))
				.flatMap(this.userRep::save)
				.map(EntityDto::toDto);

	}
	
	public Mono<Void> deleteUser(Long id){
        return this.userRep.deleteById(id);
    }
}
