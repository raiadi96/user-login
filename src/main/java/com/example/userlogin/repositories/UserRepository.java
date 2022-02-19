package com.example.userlogin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.userlogin.io.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	

}
