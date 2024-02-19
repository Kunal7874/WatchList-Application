package com.example.kunnu.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	String apiUrl = "https://www.omdbapi.com/?apikey=a8431cee&t=";
	
	public String getMovieRating(String title)
	{
		try {
			//try to catch rating by calling OMDB Api 
			RestTemplate template=new RestTemplate();
			
			//apiUrl+title
			ResponseEntity<ObjectNode>response=template.getForEntity(apiUrl+title, ObjectNode.class);
			ObjectNode jsonObject=response.getBody();
			return jsonObject.path("imdbRating").asText();
			
		}
		catch (Exception e) {
			//to user entered rating will be taken
			System.out.println("Either movie not found or API is down" + e.getMessage());
			return null;
		}
	}

}
