package com.example.snowhoundback.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
  @Query("SELECT f FROM Movie f where f.EIDR = ?1")
  Optional<Movie> findFilmByEIDR(String eidr);
}
