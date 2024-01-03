package com.example.snowhoundback.movie;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Movie {
  @Id
  @GeneratedValue
  private Integer id;

  @Column (nullable = false)
  private String name;

  @Column (nullable = false, unique = true)
  private String EIDR;

  @Column (nullable = false)
  private Double rating = 0.0;

  @Column (nullable = true)
  private Integer year;

  @Column (nullable = false)
  private Boolean status = false;

  @Column (nullable = true)
  private Set<Category> categories;
  public Movie() {
  }

  public Movie(Integer id, String name, String EIDR, Double rating, Integer year, Boolean status, Set<Category> categories) {
    this.id = id;
    this.name = name;
    this.EIDR = EIDR;
    this.rating = rating;
    this.year = year;
    this.status = status;
    this.categories = categories;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEIDR() {
    return EIDR;
  }

  public void setEIDR(String EIDR) {
    this.EIDR = EIDR;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  @Override
  public String toString() {
    return "Movie{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", EIDR='" + EIDR + '\'' +
            ", rating=" + rating +
            ", year=" + year +
            ", status=" + status +
            ", categories=" + getCategories() +
            '}';
  }
}
