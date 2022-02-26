package com.example.userlogin.services.implementation;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userlogin.dto.UserDto;
import com.example.userlogin.io.entity.UserEntity;
import com.example.userlogin.model.response.ExceptionResponseModel;
import com.example.userlogin.repositories.UserRepository;
import com.example.userlogin.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository _repo;
	
	@Autowired
	BCryptPasswordEncoder _passwordEncoder;
	
	
	@Override
	public UserDto createUser(UserDto input_dto) {
		// TODO Auto-generated method stub
		if(_repo.findByEmail(input_dto.getEmail()) != null) throw new RuntimeException("Email Address already Exists!");
		
		UserEntity  userEntity = new UserEntity();
		BeanUtils.copyProperties(input_dto, userEntity);
		
		userEntity.setUser_id(UUID.randomUUID().toString());
		userEntity.setEncrypted_password(_passwordEncoder.encode(input_dto.getPassword()));
		UserEntity response_entity = _repo.save(userEntity);
		UserDto response_dto = new UserDto();
		BeanUtils.copyProperties(response_entity, response_dto);
		
		return response_dto;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user = _repo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getEmail(), user.getEncrypted_password(), new ArrayList<>());
	}


	@Override
	public UserDto getUserByEmail(String email) {
		//
		UserEntity user = _repo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		UserDto response_dto = new UserDto();
		BeanUtils.copyProperties(user, response_dto);
		return response_dto;
	}


	@Override
	public UserDto getUserById(String user_id) {
		UserEntity user = _repo.findByUserId(user_id);
		if(user == null)
			throw new UsernameNotFoundException(user_id);
		UserDto responseDto = new UserDto();
		BeanUtils.copyProperties(user, responseDto);
		return responseDto;
	}
	
}
