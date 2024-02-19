package com.example.kunnu.watchlist.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kunnu.watchlist.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
