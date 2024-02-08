package com.Travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Travel.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
	
//	@Query(value = "select * from dealer as d ,address as a where d.phone_number=a.phone_number",nativeQuery = true)
//	List<Dealer> getAllDealers();
	
	@Query(value = "SELECT * FROM user_info as u where u.mobile_number=:mobile", nativeQuery = true)
	Optional<UserInfo> findByMobileNumber(@Param("mobile")String string);
	
	@Query(value = "SELECT * FROM user_info as u where u.user_email=:email", nativeQuery = true)
	Optional<UserInfo> findByEmailId(@Param("email")String userEmail);
	
	//username
	@Query(value = "SELECT * FROM user_info as u where u.user_name=:userName", nativeQuery = true)
	Optional<UserInfo> findByUserName(@Param("userName")String userName);
	@Query("SELECT u FROM UserInfo u WHERE u.userName = :userName AND u.password = :password")
	UserInfo findByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);
	

}
