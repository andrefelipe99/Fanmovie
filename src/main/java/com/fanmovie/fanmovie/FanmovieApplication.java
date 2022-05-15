package com.fanmovie.fanmovie;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fanmovie.fanmovie.resource.RequestMovie;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

@SpringBootApplication
public class FanmovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanmovieApplication.class, args);
		
		//System.out.println("Printar algo");
		
		//TmdbMovies movies = new TmdbApi("a2f88bffa883d520f4fbd55a6045d697").getMovies();
		//MovieDb movie = movies.getMovie(550, "pt-BR");
		//MovieResultsPage mR = new RequestMovie().searchMovieByName("Clube da luta");
		//List<MovieDb> result = mR.getResults();
		//System.out.println(result.get(1).getImdbID());
		//System.out.println(movie.getGenres());
		
		
	}

}
