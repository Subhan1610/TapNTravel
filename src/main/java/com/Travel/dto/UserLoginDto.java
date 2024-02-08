package com.Travel.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserLoginDto {
	private int userId;
	private String userName;
	private String mobileNumber;
	private String userEmail;

}
