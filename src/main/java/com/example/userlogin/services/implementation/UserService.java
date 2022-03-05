package com.example.userlogin.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userlogin.dto.UserDto;
import com.example.userlogin.exceptions.UserServiceException;
import com.example.userlogin.io.entity.AddressEntity;
import com.example.userlogin.io.entity.UserEntity;
import com.example.userlogin.model.response.ExceptionResponseEnum;
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
		ModelMapper modelMapper = new ModelMapper();

		UserEntity  userEntity = modelMapper.map(input_dto, UserEntity.class);
//		BeanUtils.copyProperties(input_dto, userEntity);
		
		userEntity.setUser_id(UUID.randomUUID().toString());
		for(AddressEntity adress: userEntity.addresses) {
			adress.setAddressId(UUID.randomUUID().toString());
		}
		userEntity.setEncrypted_password(_passwordEncoder.encode(input_dto.getPassword()));
		UserEntity response_entity = _repo.save(userEntity);
		UserDto response_dto = modelMapper.map(response_entity, UserDto.class);
//		BeanUtils.copyProperties(response_entity, response_dto);
		
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
		ModelMapper modelMapper = new ModelMapper();
		UserDto response_dto = modelMapper.map(user, UserDto.class);
//		BeanUtils.copyProperties(user, response_dto);
		return response_dto;
	}


	@Override
	public UserDto getUserById(String user_id) {
		UserEntity user = _repo.findByUserId(user_id);
		if(user == null)
			throw new UsernameNotFoundException(user_id);
		ModelMapper modelMapper = new ModelMapper();
		UserDto responseDto = modelMapper.map(user, UserDto.class);
//		BeanUtils.copyProperties(user, responseDto);
		return responseDto;
	}


	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		UserEntity userEntity = _repo.findByUserId(userId);
		if(userEntity == null) throw new UserServiceException(ExceptionResponseEnum.NO_RECORD_FOUND.getMessage());
		
		userEntity.setFirst_name(userDto.getFirst_name());
		userEntity.setLast_name(userDto.getLast_name());
		UserEntity responseEntity = _repo.save(userEntity);
		ModelMapper modelMapper = new ModelMapper();
		UserDto responseDto = modelMapper.map(responseEntity, UserDto.class);
//		BeanUtils.copyProperties(responseEntity, responseDto);
		return responseDto;
	}


	@Override
	public void deleteUser(String userId) {
		UserEntity user = _repo.findByUserId(userId);
		
		if(user == null) throw new UserServiceException(ExceptionResponseEnum.NO_RECORD_FOUND.getMessage());
		_repo.delete(user);
		
	}


	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		Pageable page_service = PageRequest.of(page, limit);
		List<UserEntity> entity_res = _repo.findAll(page_service).getContent();
		List<UserDto> res = new ArrayList<UserDto>();
		ModelMapper modelMapper = new ModelMapper();
		for(UserEntity entity: entity_res) {
			UserDto userdto =  modelMapper.map(entity, UserDto.class);
//			BeanUtils.copyProperties(entity, userdto);
			res.add(userdto);
		}
		
		return res;
	}
	
}
