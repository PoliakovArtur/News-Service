package com.example.dto.news.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateNewsDto {

    @Schema(description = "Заголовок новости", example = "Биатлон")
    @NotNull(message = "title является обязательным параметром")
    @NotBlank(message = "Нельзя создать новость с пустым заголовком")
    private String title;

    @Schema(description = "Содержание новости", example = "Новость о спорте")
    @NotNull(message = "content является обязательным параметром")
    @NotBlank(message = "Нельзя создать новость с пустым содержанием")
    private String content;

    @Schema(description = "Id категории в которой создается новость", example = "1")
    @NotNull(message = "categoryId является обязательным параметром")
    private Long categoryId;

    @Schema(description = "Id пользователя создающего новость", example = "1")
    @NotNull(message = "userId является обязательным параметром")
    private Long userId;
}
