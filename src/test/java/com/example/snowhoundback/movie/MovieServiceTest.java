package com.example.snowhoundback.movie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
public class MovieServiceTest {

  static final MySQLContainer MY_SQL_CONTAINER;

  @Autowired
  WebTestClient webTestClient;

  @Autowired
  private MovieRepository movieRepository;

  static {
    MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
    MY_SQL_CONTAINER.start();
  }

  @DynamicPropertySource
  static void configureTestProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> MY_SQL_CONTAINER.getJdbcUrl());
    registry.add("spring.datasource.username", () -> MY_SQL_CONTAINER.getUsername());
    registry.add("spring.datasource.password", () -> MY_SQL_CONTAINER.getPassword());
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
  }

  @BeforeEach
  public void beforeEach() {
    Set<Category> categories = new HashSet<>();
    categories.add(Category.HORROR);
    categories.add(Category.DRAMA);

    Movie movie = Movie.builder().name("Test movie").EIDR("test-eidr-code-1234").rating(5.5).year(2024).status(false).categories(categories).build();

    movieRepository.save(movie);
  }

  @AfterEach
  public void afterEach() {
    movieRepository.deleteAll();
  }

  @Test
  public void AddNewFilm_Test() {
    Set<Category> categories = new HashSet<>();
    categories.add(Category.COMEDY);
    categories.add(Category.FANTASY);

    Movie movie = Movie.builder().name("Test movie 2").EIDR("test-eidr-code-4321").rating(7.5).year(2023).status(false).categories(categories).build();

    webTestClient.post()
            .uri("api/movies")
            .bodyValue(movie)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(Movie.class)
            .consumeWith(movieEntity -> Assertions.assertNotNull(movieEntity.getResponseBody().getId()));
  }

  @Test
  public void GetFilms_Test() {
    Set<Category> categories = new HashSet<>();
    categories.add(Category.HORROR);
    categories.add(Category.DRAMA);

    webTestClient.get()
            .uri("api/movies")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBodyList(Movie.class)
            .consumeWith(listOfMovies -> {
              var list = listOfMovies.getResponseBody();
              Assertions.assertEquals(1, list.size());
              Assertions.assertEquals(categories, list.get(0).getCategories());
            });
  }
}
