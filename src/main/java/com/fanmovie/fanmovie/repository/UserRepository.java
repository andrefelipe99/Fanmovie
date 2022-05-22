package com.fanmovie.fanmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fanmovie.fanmovie.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value="SELECT * from users where email = :email and password = :password", nativeQuery = true)
	public User Login(String email, String password);
	
	User findById(long id);
	
	User findByEmail(String email);
}
