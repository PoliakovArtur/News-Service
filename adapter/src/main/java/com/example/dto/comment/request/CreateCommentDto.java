package com.example.dto.comment.request;

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
public class CreateCommentDto {
    @Schema(description = "Содержание комментария", example = "Хорошая новость")
    @NotNull(message = "content является обязательным параметром")
    @NotBlank(message = "Нельзя создать пустой комментарий")
    private String content;

    @Schema(description = "Id новости к которой пишется комментарий", example = "1")
    @NotNull(message = "newsId является обязательным параметром")
    private Long newsId;

    @Schema(description = "Id пользователя, написавшего комментарий", example = "1")
    @NotNull(message = "userId является обязательным параметром")
    private Long userId;
}
