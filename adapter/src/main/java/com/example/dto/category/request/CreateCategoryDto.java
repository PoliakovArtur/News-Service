package com.example.dto.category.request;

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
public class CreateCategoryDto {
    @Schema(description = "Заголовок категории", example = "Спорт")
    @NotNull(message = "content является обязательным параметром")
    @NotBlank(message = "Нельзя создать категорию с пустым заголовком")
    private String title;

    @Schema(description = "Описание категории", example = "Новости о спорте")
    @NotNull(message = "content является обязательным параметром")
    @NotBlank(message = "Нельзя создать категорию с пустым описанием")
    private String description;

    @Schema(description = "Id пользователя, создающего категорию", example = "1")
    @NotNull(message = "userId является обязательным параметром")
    private Long userId;
}
