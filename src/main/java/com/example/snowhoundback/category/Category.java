package com.example.snowhoundback.category;

import com.example.snowhoundback.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Category {
  @Id
  @GeneratedValue
  private Integer id;

  @Column (nullable = false)
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "categories")
  private Set<Movie> movies;

  public Category() {
  }

  public Category(Integer id) {
    this.id = id;
  }

  public Category(String name) {
    this.name = name;
  }

  public Category(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
