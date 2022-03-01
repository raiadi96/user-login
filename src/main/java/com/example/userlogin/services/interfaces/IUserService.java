package com.example.userlogin.services.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.userlogin.dto.UserDto;

public interface IUserService extends UserDetailsService {

	public UserDto createUser(UserDto input_dto);
	public UserDto getUserByEmail(String email);
	public UserDto getUserById(String user_id);
	public UserDto updateUser(String userId, UserDto userDto);
	public void deleteUser(String userId);
	public List<UserDto> getUsers(int page, int limit);
	
}
