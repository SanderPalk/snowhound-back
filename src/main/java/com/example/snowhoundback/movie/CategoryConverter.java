package com.example.snowhoundback.movie;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Set<Category>, String> {

  private static final String DELIMITER = ",";

  @Override
  public String convertToDatabaseColumn(Set<Category> categories){
    if ( categories == null || categories.isEmpty()) {
      return null;
    }
    return categories.stream()
            .map(Enum::name)
            .collect(Collectors.joining(DELIMITER));
  }

  @Override
  public Set<Category> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isEmpty()) {
      return new HashSet<>();
    }
    return Arrays.stream(dbData.split(DELIMITER))
            .map(Category::valueOf)
            .collect(Collectors.toSet());
  }
}
