package com.example.dto.news.request;

import com.example.dto.UpdateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateNewsDto extends UpdateDto {

    @Schema(description = "Заголовок новости", example = "Биатлон")
    @NotBlank(message = "Нельзя изменить заголовок новости на пустое значение")
    private String title;

    @Schema(description = "Содержание новости", example = "Новость о спорте")
    @NotNull(message = "content является обязательным параметром")
    @NotBlank(message = "Нельзя изменить содержание новости на пустое значение")
    private String content;

    @Schema(description = "Id категории в которой создается новость", example = "1")
    @NotNull(message = "categoryId является обязательным параметром")
    private Long categoryId;
}