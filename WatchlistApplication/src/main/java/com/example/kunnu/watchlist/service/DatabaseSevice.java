package com.example.kunnu.watchlist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kunnu.watchlist.entity.Movie;
import com.example.kunnu.watchlist.reposiitory.MovieRepo;

@Service
public class DatabaseSevice {
	
	
	@Autowired
	MovieRepo movieRepo;
	@Autowired
	RatingService ratingService;
	public void create(Movie movie)
	{
		String rating = ratingService.getMovieRating(movie.getTitle());
		if(rating != null) {
			movie.setRating(Float.parseFloat(rating));
		}
		movieRepo.save(movie);
	}
	
	
	public List<Movie> getAllMovies()
	{
		return movieRepo.findAll();
	}
	
	public Movie getMovieById(Integer id)
	{
		return movieRepo.findById(id).get();
		
	}


	public void update(Movie movie, Integer id) {
		Movie toBeUpdated=getMovieById(id);
		toBeUpdated.setTitle(movie.getTitle());
		toBeUpdated.setComment(movie.getComment());
		toBeUpdated.setPriority(movie.getPriority());
		toBeUpdated.setRating(movie.getRating());
		
		movieRepo.save(toBeUpdated);
	}

}
