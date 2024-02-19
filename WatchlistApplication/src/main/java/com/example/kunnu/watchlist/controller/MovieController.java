package com.example.kunnu.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.kunnu.watchlist.entity.Movie;
import com.example.kunnu.watchlist.service.DatabaseSevice;

import jakarta.validation.Valid;


@RestController
public class MovieController {
	@Autowired
	DatabaseSevice databaseSevice;
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchListForm(@RequestParam(required = false) Integer id)
	{
		
		
		String viewName="watchlistItemForm";
		Map<String, Object> model=new HashMap<>();
		
		if(id==null)
		{
			model.put("watchlistItem",  new Movie());
		}
		else {
			model.put("watchlistItem", databaseSevice.getMovieById(id));
		}
		return new ModelAndView(viewName,model);
	}

	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			//If error comes then , redisplay the form and let user enter again
			return new ModelAndView("watchlistItemForm");
		}
		Integer id=movie.getId();
		if(id==null)
		{
			databaseSevice.create(movie);
		}else {
			databaseSevice.update(movie,id);
		}
		
		
		//for redirecting 
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchlist");
		
		return new ModelAndView(rd);
		
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist()
	{
		String viewName="watchlist";
		Map<String, Object>model=new HashMap<>();
		List<Movie>movieList=databaseSevice.getAllMovies();
		model.put("watchlistrows", movieList);
		model.put("noofmovies", movieList.size());
		
		return new ModelAndView(viewName,model);
		
	}
}
