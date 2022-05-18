package com.fanmovie.fanmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fanmovie.fanmovie.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
