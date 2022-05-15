package com.fanmovie.fanmovie.models;

import java.util.List;
import java.util.Map;

import info.movito.themoviedbapi.model.MovieDb;

public class Movie {
	
	//Vai servir pra linkar o filme a um perfil de usuário
	private String idUsuario;
	
	private String title;
	private String imdb_id;
	private String original_lang;
	private String overview;
	private String status;
	private List<String> categoryList;
	private String release_date;
	private int id;
	private int duracao_min;
	private float vote_average;
	
	private Map<Integer, String> genre;

	
	public Movie() {
		
	}
	
	
	public Movie(MovieDb movie ,List<String> categoryList, String idUsuario) {
		this.categoryList = categoryList;
		this.title = movie.getTitle();
		this.imdb_id = movie.getImdbID();
		this.original_lang = movie.getOriginalLanguage();
		this.overview = movie.getOverview();
		this.status = movie.getStatus();
		this.release_date = movie.getReleaseDate();
		this.id = movie.getId();
		this.duracao_min = movie.getRuntime();
		this.vote_average = movie.getVoteAverage();
		this.idUsuario = idUsuario;
	}



	//getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdb_id() {
		return imdb_id;
	}

	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}

	public String getOriginal_lang() {
		return original_lang;
	}

	public void setOriginal_lang(String original_lang) {
		this.original_lang = original_lang;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracao_min() {
		return duracao_min;
	}

	public void setDuracao_min(int duracao_min) {
		this.duracao_min = duracao_min;
	}

	public float getVote_average() {
		return vote_average;
	}

	public void setVote_average(float vote_average) {
		this.vote_average = vote_average;
	}

	public Map<Integer, String> getGenre() {
		return genre;
	}

	public void setGenre(Map<Integer, String> genre) {
		this.genre = genre;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}


	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
