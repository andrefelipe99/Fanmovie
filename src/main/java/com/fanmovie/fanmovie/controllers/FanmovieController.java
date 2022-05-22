package com.fanmovie.fanmovie.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fanmovie.fanmovie.models.Movie;
import com.fanmovie.fanmovie.repository.MovieRepository;
import com.fanmovie.fanmovie.resource.RequestMovie;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

@Controller
public class FanmovieController {
	
	@Autowired
	private MovieRepository mr;	
	private List<MovieDb> movieList;
	private long idUsuarioAtual = 1;

	@ModelAttribute
	public MovieDb init() {
		return new MovieDb();
	}

	@RequestMapping(value = "/home/{id}", method = RequestMethod.GET)
	public String indexHome(@PathVariable("id") long id) {
		this.idUsuarioAtual = id;
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(RedirectAttributes attributes) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		return "/home";
	}
	
	@RequestMapping(value = "/deslogar", method = RequestMethod.GET)
	public String deslogar() {
		this.idUsuarioAtual = -1;
		return "redirect:/";
	}
	
	// Função que faz a 1pesquisa de um filme
	@PostMapping("/showResult")
	public String searchMovie(@RequestParam(name = "movieName", required = false) String movieName, Model model, RedirectAttributes attributes) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		if (movieName.isBlank()) {
			attributes.addFlashAttribute("flashMessage", "Campo de busca vazio!");
			attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/home";
		} else {
			// Função que vai fazer a pesquisa de acordo com o nome do filme que o usuario
			// deu
			MovieResultsPage resultSearch = new RequestMovie().searchMovieByName(movieName);
			
			movieList = resultSearch.getResults();
			
			for(int i= 0; i < movieList.size(); i++ ) {
                movieList.get(i).setPosterPath("https://image.tmdb.org/t/p/w300" + movieList.get(i).getPosterPath());
            }

			if(movieList.size() == 0) {
				attributes.addFlashAttribute("flashMessage", "Não foi encontrado nenhum filme!");
				attributes.addFlashAttribute("flashType", "danger");
				return "redirect:/home";
			}

			model.addAttribute("movieList", movieList);
			// System.out.println("Post realizado " + resultSearch.getResults().get(0));

			return "/showResult";
		}
	}

	// adicionar filme como "completo"
	@RequestMapping(value = "/addCompleteMovie/{codigo}", method = RequestMethod.GET)
	public String adicionarMovieComplete(@PathVariable("codigo") int codigo, RedirectAttributes attributes) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);
		List<Movie> movieList = mr.findAll();
		boolean existe = false;
		
		for (Movie m : movieList) {
			if(m.getCodigo() == codigo && m.getCategory().equalsIgnoreCase("Completo") && m.getIdUsuario() == this.idUsuarioAtual) {
				attributes.addFlashAttribute("flashMessage", "Este filme já foi adicionado nesta lista!");
				attributes.addFlashAttribute("flashType", "danger");				
				existe = true;
				return "redirect:/showResult";
			}
		}
		
		if(!existe) {
			Movie movie = new Movie(movieDb, "Completo", this.idUsuarioAtual);
			mr.save(movie);
		}	
				
		return "redirect:/showResult";
	}

	// adicionar filme como "planejo assistir"
	@RequestMapping(value = "/PlanAssistirMovie/{codigo}", method = RequestMethod.GET)
	public String adicionarMoviePlanAssistir(@PathVariable("codigo") int codigo) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);
		List<Movie> movieList = mr.findAll();
		boolean existe = false;
		
		for (Movie m : movieList) {
			if(m.getCodigo() == codigo && m.getCategory().equalsIgnoreCase("Planejo Assistir") && m.getIdUsuario() == this.idUsuarioAtual) {
				existe = true;
			}
		}
		
		if(!existe) {
			Movie movie = new Movie(movieDb, "Planejo Assistir", this.idUsuarioAtual);
			mr.save(movie);
		}
		
		return "redirect:/showResult";
	}

	// adicionar filme como "favorito"
	@RequestMapping(value = "/favoritoMovie/{codigo}", method = RequestMethod.GET)
	public String adicionarMovieFavorito(@PathVariable("codigo") int codigo) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}

		MovieDb movieDb = new RequestMovie().getMovieByInternalId(codigo);
		List<Movie> movieList = mr.findAll();
		boolean existe = false;
		
		for (Movie m : movieList) {
			if(m.getCodigo() == codigo && m.getCategory().equalsIgnoreCase("Favorito") && m.getIdUsuario() == this.idUsuarioAtual) {
				existe = true;
			}
		}
		
		if(!existe) {
			Movie movie = new Movie(movieDb, "Favorito", this.idUsuarioAtual);
			mr.save(movie);
		}			
		
		return "redirect:/showResult";
	}

	@RequestMapping("/removerMovie/{codigo}")
	public String removerMovie(@PathVariable("codigo") int codigo) {
		// falta criar a função de crud para excluir
		Movie m = mr.findById((long)codigo);
		
		if(m.getCategory().equalsIgnoreCase("Favorito") && m.getIdUsuario() == this.idUsuarioAtual) {
			mr.delete(m);
			return "redirect:/favorito";
		} else if(m.getCategory().equalsIgnoreCase("Completo") && m.getIdUsuario() == this.idUsuarioAtual) {
			mr.delete(m);
			return "redirect:/completo";
		} else if(m.getCategory().equalsIgnoreCase("Planejo Assistir") && m.getIdUsuario() == this.idUsuarioAtual) {
			mr.delete(m);
			return "redirect:/planAssistir";
		}
		return "redirect:/home";
	}

	// decidindo se irei realmente precisar dessa função
	@GetMapping("/showResult")
	public String showResult(Model model) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		if(this.movieList != null) {
			model.addAttribute("movieList", movieList);
		}
		return "/showResult";
	}
	
	@GetMapping("/planAssistir")
	public String telaPlanAssistir(Model model) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		List<Movie> allMoviesList = mr.findAll();
		List<Movie> moviesCompleteList = new ArrayList<>();
						
		for (Movie m : allMoviesList) {
			if(m.getCategory().equalsIgnoreCase("Planejo Assistir") && m.getIdUsuario() == this.idUsuarioAtual) {
				moviesCompleteList.add(m);
			}
		}
	
		model.addAttribute("movieList",moviesCompleteList);
		return "/planAssistir";
	}
	
	@GetMapping("/completo")
	public String telacompleto(Model model) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		List<Movie> allMoviesList = mr.findAll();
		List<Movie> moviesCompleteList = new ArrayList<>();
		
		for (Movie m : allMoviesList) {
			if(m.getCategory().equalsIgnoreCase("Completo") && m.getIdUsuario() == this.idUsuarioAtual) {
				moviesCompleteList.add(m);
			}
		}
		model.addAttribute("movieList",moviesCompleteList);
		return "/completo";
	}
	
	@GetMapping("/favorito")
	public String telaFavorito(Model model) {
		if(this.idUsuarioAtual == -1) {
			return "redirect:/";
		}
		
		List<Movie> allMoviesList = mr.findAll();
		List<Movie> moviesCompleteList = new ArrayList<>();
		
		for (Movie m : allMoviesList) {
			if(m.getCategory().equalsIgnoreCase("Favorito") && m.getIdUsuario() == this.idUsuarioAtual) {
				moviesCompleteList.add(m);
			}
		}
		model.addAttribute("movieList",moviesCompleteList);
		return "/favorito";
	}
}
