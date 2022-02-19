package com.example.userlogin.services.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userlogin.dto.UserDto;
import com.example.userlogin.io.entity.UserEntity;
import com.example.userlogin.repositories.UserRepository;
import com.example.userlogin.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository _repo;
	
	
	@Override
	public UserDto createUser(UserDto input_dto) {
		// TODO Auto-generated method stub
		UserEntity  userEntity = new UserEntity();
		BeanUtils.copyProperties(input_dto, userEntity);
		userEntity.setUser_id("test_user_id");
		userEntity.setEncrypted_password("test_encryption");
		
		UserEntity response_entity = _repo.save(userEntity);
		UserDto response_dto = new UserDto();
		BeanUtils.copyProperties(response_entity, response_dto);
		
		return response_dto;
	}

}
