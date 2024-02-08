package com.Travel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Travel.dto.UserLoginDto;
import com.Travel.entity.UserInfo;
import com.Travel.exception.ValidationException;

import com.Travel.service.UserInfoService;
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/allUsers")
	public ResponseEntity<List<UserInfo>> getAllUsers() {
		List<UserInfo> allUsers = userInfoService.findAllUser();
		return new ResponseEntity<List<UserInfo>>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserInfo> retrieveUser(@PathVariable Integer id) {
		Optional<UserInfo> user = userInfoService.findUserById(id);

		if (user.isEmpty())
			throw new ValidationException("Unable to fetch the user id-" + id);
		return new ResponseEntity<UserInfo>(user.get(), HttpStatus.OK);
	}

	@PostMapping(value = "/createUser/{initBalance}")
	public ResponseEntity<?> createUserInfo(@RequestBody @Valid UserInfo userInfo, @PathVariable int initBalance) {

		return new ResponseEntity<>(userInfoService.createUser(userInfo, initBalance), HttpStatus.OK);

	}

//	@DeleteMapping("/users/{id}")
//	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
//		return userInfoService.deleteUserById(id);
//	}

	@PutMapping("/users/{id}")

	public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user, @PathVariable Integer id) {

		UserInfo updateUserById = userInfoService.updateUserById(user, id);

		return new ResponseEntity<UserInfo>(updateUserById, HttpStatus.OK);

	}
	
	
	 @PostMapping("/login/{username}/{password}")
	    public ResponseEntity<UserLoginDto> loginUser(@PathVariable String username, @PathVariable String password) {
		 return new ResponseEntity<UserLoginDto>(userInfoService.loginCustomer(username, password), HttpStatus.OK);
	    }
}
