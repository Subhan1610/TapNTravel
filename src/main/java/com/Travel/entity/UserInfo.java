package com.Travel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int userId;
	@NotEmpty(message = "Please provide a name")
	@Size(min = 2, max = 25, message = "User Name should be Min 2 and Max 25 : ")
	private String userName;

	@NotNull
	@Pattern(regexp = "^[1-9]\\d{9}$", message = "Mobile Number must be a 10-digit number and It Should not start with Zero")
	@Length(max = 10)
	private String mobileNumber;

	@Email(message = "Check email address")
	@Length(max = 100)
	private String userEmail;

	@OneToOne(mappedBy = "userInfo")
	@JsonManagedReference()
	private TapCard tapCard;
	
	@NotEmpty(message = "Please provide a password")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters")
    private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public TapCard getTapCard() {
		return tapCard;
	}

	public void setTapCard(TapCard tapCard) {
		this.tapCard = tapCard;
	}

}
