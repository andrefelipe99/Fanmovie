package com.fanmovie.fanmovie.models;

import java.util.Date;
import java.util.Map;

public class Movie {
	
	private String title;
	private String imdb_id;
	private String original_lang;
	private String overview;
	private String status;
	private Date release_date;
	private int id;
	private int duracao_min;
	private float vote_average;
	
	private Map<Integer, String> genre;

	
	public Movie() {
		
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

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
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
	
	
}
