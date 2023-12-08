package com.example.snowhoundback.movie;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping
  public void registerNewMovies(@RequestBody Movie film) {
    movieService.addNewFilm(film);
  }

  @DeleteMapping(path = "{movie_id}")
  public void deleteMovie(@PathVariable Integer movie_id) {
    movieService.deleteMovie(movie_id);
  }

  @PutMapping(path = "{movie_id}/status")
  public void updateMovieStatus(@PathVariable("movie_id") Integer movie_id, @RequestBody String status) {
    System.out.println(status);
    movieService.updateMovieStatus(movie_id, status);
  }
}
