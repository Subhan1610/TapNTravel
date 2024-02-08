package com.Travel.service;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Travel.dto.UserLoginDto;
import com.Travel.entity.TapCard;
import com.Travel.entity.TransactionHistory;
import com.Travel.entity.UserInfo;
import com.Travel.exception.ValidationException;
import com.Travel.repository.TapCardRepository;
import com.Travel.repository.TransactionHistoryRepository;
import com.Travel.repository.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private TapCardRepository tapCardRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TransactionHistoryRepository walletTransactionRepository;

	@Override
	public List<UserInfo> findAllUser() {
		return userInfoRepository.findAll();
	}

	@Override
	public Optional<UserInfo> findUserById(Integer id) {
		return userInfoRepository.findById(id);
	}

	@Override
	public UserInfo createUser(UserInfo userInfo, int initBalance) {

		if (userInfo.getUserName() == null) {
			throw new ValidationException("UserName Value should not be Null");
		}

		Optional<UserInfo> isMobileRegistered = userInfoRepository.findByMobileNumber(userInfo.getMobileNumber());

		if (isMobileRegistered.isPresent()) {
			throw new ValidationException("Mobile Number is already registered ");
		}

		Optional<UserInfo> isEmailRegistered = userInfoRepository.findByEmailId(userInfo.getUserEmail());
		if (isEmailRegistered.isPresent()) {
			throw new ValidationException("User Email is already registered ");
		}

		Optional<UserInfo> isUserNameRegistered = userInfoRepository.findByUserName(userInfo.getUserName());

		if (isUserNameRegistered.isPresent()) {

			throw new ValidationException("User Name is already taken ");

		}

		  
		TapCard card = new TapCard();

		if (initBalance >= 200) {

			userInfoRepository.save(userInfo);

			card.setTabBalance(initBalance);
			card.setUserInfo(userInfo);

			card.setStatus("New Card");
			tapCardRepository.save(card);

			userInfo.setTapCard(card);

			// WalletTransaction

			TransactionHistory walletTransaction = new TransactionHistory();

			walletTransaction.setTapCard(card);

			walletTransaction.setCreditAmount(initBalance);

			walletTransaction.setClosingBalance(initBalance);

			walletTransactionRepository.save(walletTransaction);

		} else {
			throw new ValidationException("Initial Balance Should be Greater than 200");
		}

		return userInfo;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteUserById(Integer id) {

		try {
			userInfoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public UserInfo updateUserById(UserInfo user, Integer id) {

		Optional<UserInfo> existingUser = userInfoRepository.findById(id);
		user.setUserId(id);
		if (existingUser.isPresent()) {
			UserInfo userInfo = existingUser.get();
			if (userInfo.getUserName() == null) {
				throw new ValidationException("Please Enter Valid UserName");
			}

			Optional<UserInfo> isMobileRegistered = userInfoRepository.findByMobileNumber(user.getMobileNumber());
			if (isMobileRegistered.isPresent() && isMobileRegistered.get().getUserId() != id) {
				throw new ValidationException("Mobile Number is already registered ");
			}

			Optional<UserInfo> isEmailRegistered = userInfoRepository.findByEmailId(user.getUserEmail());
			if (isEmailRegistered.isPresent() && isEmailRegistered.get().getUserId() != id) {
				throw new ValidationException("User Email is already registered ");
			}

			Optional<UserInfo> isUserNameRegistered = userInfoRepository.findByUserName(user.getUserName());
			if (isUserNameRegistered.isPresent() && isUserNameRegistered.get().getUserId() != id) {
				throw new ValidationException("User Name is already taken ");
			}
			user.setTapCard(existingUser.get().getTapCard());
			userInfoRepository.save(user);
		}
		return user;
	}

//	@Override
//	public Optional<UserInfo> findByUsernameAndPassword(String userName, String password) {
//		if (userName == null || password == null || userName.trim().isEmpty() || password.trim().isEmpty()) {
//	        throw new IllegalArgumentException("UserName and password cannot be null or empty");
//	    }
//		
//		Optional<UserInfo> user = ( userInfoRepository).findByUsernameAndPassword(userName, password);
//		
//		if (!user.isPresent()) {
//	        throw new ValidationException("Invalid username or password");
//	    }
//		return user;
//	}
	
	
	@Override
	public UserLoginDto loginCustomer(String userName, String password) {
		 
		UserInfo customers = userInfoRepository.findByUsernameAndPassword(userName, password);
		UserLoginDto userDto = null;
		List<UserInfo> customer = userInfoRepository.findAll();
 
		boolean isDuplicate = customer.stream().anyMatch(
				customer1 -> customer1.getUserName().equals(userName) && customer1.getPassword().equals(password));
 
		if (!isDuplicate) {
			throw new ValidationException("Invalid Username or Password ");
		}
 
		userDto= mapper.map(customers, UserLoginDto.class);
		
		return userDto;
	}
 
}
