package com.fanmovie.fanmovie.resource;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbFind;
import info.movito.themoviedbapi.TmdbFind.ExternalSource;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class RequestMovie {
	
	private String apiKey;
	
	
	public RequestMovie() {
		
	}
	
	public RequestMovie(String apiKey) {
		this.apiKey = apiKey;
	}

	//pesquisa o filme pelo nome, retornará uma lista com filmes resultantes da pesquisa
	public MovieResultsPage searchMovieByName(String movieName) {
		TmdbSearch search = new TmdbApi(this.apiKey).getSearch();
		
		MovieResultsPage resultPage = search.searchMovie(movieName, null, "pt-BR", false, null);
		
		return resultPage;
	}
	
	//busca o filme pelo id do banco de dados, retorna informações mais completas do filme
	public MovieDb getMovieByInternalId(int id) {
		TmdbMovies movies = new TmdbApi(this.apiKey).getMovies();
		
		MovieDb movie = movies.getMovie(id, "pt-BR");
		
		return movie;
	}
	
	//encontra por ID externo um filme, aceita ID do IMDB.
	public List<MovieDb> findMovieByExternalId(String idExternal) {
		List<MovieDb> movieResults = new ArrayList<MovieDb>();
		
		//retorna uma instancia de TmdbFind através da classe principal da api(TmdbApi)
		TmdbFind findMovie = new TmdbApi(this.apiKey).getFind();
		
		FindResults findResults = findMovie.find(idExternal, ExternalSource.imdb_id, "pt-BR");
		
		if(findResults.getMovieResults() != null) {
			movieResults.addAll(findResults.getMovieResults());
		}
		
		return movieResults;
	}

	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	
}
