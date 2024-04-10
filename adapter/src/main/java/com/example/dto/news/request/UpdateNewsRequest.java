package com.example.dto.news.request;

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
public class UpdateNewsRequest extends UpdateByUserRequest {

    @Schema(description = "Заголовок новости", example = "Биатлон")
    @Pattern(regexp = ".*\\S.*", message = "Нельзя изменить заголовок новости на пустое значение")
    private String title;

    @Schema(description = "Содержание новости", example = "Новость о спорте")
    @Pattern(regexp = ".*\\S.*", message = "Нельзя изменить содержание новости на пустое значение")
    private String content;
}