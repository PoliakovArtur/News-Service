package com.example.dto.category.request;

import com.example.dto.UpdateByUserRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UpdateCategoryRequest extends UpdateByUserRequest {
    @Schema(description = "Заголовок категории", example = "Спорт")
    @Pattern(regexp = ".*\\S.*", message = "Нельзя изменить заголовок категории на пустое значение")
    private String title;

    @Schema(description = "Описание категории", example = "Новости о спорте")
    @Pattern(regexp = ".*\\S.*", message = "Нельзя изменить описание категории на пустое значение")
    private String description;
}
