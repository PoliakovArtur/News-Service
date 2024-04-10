package com.example.filter.impl;

import com.example.filter.CreateUpdateDateTimeFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CategoryFilter extends CreateUpdateDateTimeFilter {

    @Schema(description = "Имя автора категории", example = "John Doe")
    private String author;
}
