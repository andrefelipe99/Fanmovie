package com.fanmovie.fanmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fanmovie.fanmovie.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	Movie findById(long id);
}
