package com.example.snowhoundback.movie;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {
  private final MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> getFilms() {
    return movieRepository.findAll();
  }

  public void addNewFilm(Movie film) {

    Optional<Movie> filmOptional = movieRepository.findFilmByEIDR(film.getEIDR());

    if (film.getCategories().isEmpty()) {
      throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST, "No categories assigned"
      );
    }
    if (filmOptional.isPresent()) {
      throw new ResponseStatusException(
              HttpStatus.CONFLICT, "EIDR code in use"
      );
    }
    movieRepository.save(film);
  }

  public void deleteMovie(Integer movie_id) {
    boolean exists = movieRepository.existsById(movie_id);
    if (!exists) {
      throw new IllegalStateException("No movie with this id:" + movie_id);
    }
    movieRepository.deleteById(movie_id);
  }

  public void updateMovieStatus(Integer movie_id, Boolean status) {
    Movie movie = movieRepository.findById(movie_id).orElseThrow(() -> new IllegalStateException("Movie with this id does not exist: " + movie_id));
    movie.setStatus(status);
    movieRepository.save(movie);
  }
}
