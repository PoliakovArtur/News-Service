package com.example.dto.comment.request;

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
public class UpdateCommentDto extends UpdateDto {
    @Schema(description = "Содержание комментария", example = "Хорошая новость")
    @NotNull(message = "content является обязательным параметром")
    @NotBlank(message = "Нельзя изменить содержимое комментария на пустое значение")
    private String content;

    @Schema(description = "Id новости к которой пишется комментарий", example = "1")
    @NotNull(message = "newsId является обязательным параметром")
    private Long newsId;
}
