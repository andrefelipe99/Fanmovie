package com.fanmovie.fanmovie.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.ListType;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;

@Entity
@Table(name = "MOVIE")
public class Movie {
	
	//Vai servir pra linkar o filme a um perfil de usuário
	private Long idUsuario;
	
	private String title;
	private String imdb_id;
	private String original_lang;	
	@Column(columnDefinition = "TEXT")
	private String overview;
	private String status;
	private String category;
	private String release_date;
	private String posterPath;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int codigo;
	private int duracao_min;
	private float vote_average;	
	

	
	public Movie() {
		
	}
	
	
	public Movie(MovieDb movie ,String category, Long idUsuario) {
		this.category = category;
		this.title = movie.getTitle();
		this.imdb_id = movie.getImdbID();
		this.original_lang = new Locale(movie.getOriginalLanguage()).getDisplayLanguage();
		this.overview = movie.getOverview();
		this.status = movie.getStatus();
		this.release_date = movie.getReleaseDate();
		this.codigo = movie.getId();
		this.duracao_min = movie.getRuntime();
		this.vote_average = movie.getVoteAverage();
		this.idUsuario = idUsuario;
		this.posterPath = "https://image.tmdb.org/t/p/w300" + movie.getPosterPath();
		
	}


	//getters and setters
	public String getTitle() {
		return title;
	}

	public String getPosterPath() {
		return posterPath;
	}


	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
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

	public long getId() {
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

	public String getCategory() {
		return category;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
