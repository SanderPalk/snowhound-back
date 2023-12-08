package com.example.snowhoundback.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {

  @Bean
  CommandLineRunner commandLineRunner(CategoryRepository repository) {
    return args -> {
      Category action =new Category("Action");
      Category comedy =new Category("Comedy");
      Category drama =new Category("Drama");
      Category horror =new Category("Horror");
      Category fantasy =new Category("Fantasy");
      Category romance =new Category("Romance");

      repository.saveAll(
              List.of(action, comedy, drama, horror, fantasy, romance));
    };
  }
}
