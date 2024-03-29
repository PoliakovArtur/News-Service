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
public class NewsFilter extends CreateUpdateDateTimeFilter {
    @Schema(description = "Имя автора новости", example = "John Doe")
    private String author;
    @Schema(description = "Название категории", example = "Sport")
    private String category;
}
