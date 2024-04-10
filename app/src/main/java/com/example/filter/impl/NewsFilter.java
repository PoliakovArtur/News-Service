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
public class NewsFilter extends CreateUpdateDateTimeFilter {

    @Schema(description = "Имя автора новости", example = "John Doe")
    private String author;

    @Schema(description = "Название категории", example = "Sport")
    private String category;
}
