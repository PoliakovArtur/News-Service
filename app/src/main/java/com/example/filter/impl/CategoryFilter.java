package com.example.filter.impl;

import com.example.filter.CreateUpdateDateTimeFilter;
import com.example.filter.Filter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryFilter extends CreateUpdateDateTimeFilter {
    @Schema(description = "Имя автора категории", example = "John Doe")
    private String author;
}
