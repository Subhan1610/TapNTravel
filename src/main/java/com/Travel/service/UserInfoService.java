package com.Travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Travel.dto.UserLoginDto;
import com.Travel.entity.UserInfo;

public interface UserInfoService {

	public abstract List<UserInfo> findAllUser();

	public abstract Optional<UserInfo> findUserById(Integer id);

	public UserInfo createUser(UserInfo userInfo,int initBalance);

	public ResponseEntity<HttpStatus> deleteUserById(Integer id);

	public UserInfo updateUserById(UserInfo user, Integer id);
	
	
	public UserLoginDto  loginCustomer(String userName, String password);

}
