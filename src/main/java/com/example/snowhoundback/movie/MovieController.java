package com.example.snowhoundback.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping(path = "api/movies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {
  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public List<Movie> getMovies() {
    return movieService.getFilms();
  }

  @GetMapping(path = "/categories")
  public List<Category> getCategories() {
    return new ArrayList<>(EnumSet.allOf(Category.class));
  }

  @PostMapping
  public void registerNewMovies(@RequestBody Movie film) {
    movieService.addNewFilm(film);
  }

  @DeleteMapping(path = "{movie_id}")
  public void deleteMovie(@PathVariable Integer movie_id) {
    movieService.deleteMovie(movie_id);
  }

  @PutMapping(path = "{id}/status")
  public void updateMovieStatus(@PathVariable("id") Integer id, @RequestBody Movie movie) {
    System.out.println("Updating movie id: " + id + " DATA: " + movie.getStatus());
    movieService.updateMovieStatus(id, movie.getStatus());
  }
}
