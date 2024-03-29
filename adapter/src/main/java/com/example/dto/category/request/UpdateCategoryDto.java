package com.example.dto.category.request;

import com.example.dto.UpdateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryDto extends UpdateDto {
    @Schema(description = "Заголовок категории", example = "Спорт")
    @NotBlank(message = "Нельзя изменить заголовок категории на пустое значение")
    private String title;

    @Schema(description = "Описание категории", example = "Новости о спорте")
    @NotBlank(message = "Нельзя изменить описание категории на пустое значение")
    private String description;
}
