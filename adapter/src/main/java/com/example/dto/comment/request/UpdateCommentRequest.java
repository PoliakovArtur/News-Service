package com.example.dto.comment.request;

import com.example.dto.UpdateByUserRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UpdateCommentRequest extends UpdateByUserRequest {

    @Schema(description = "Содержание комментария", example = "Хорошая новость")
    @Pattern(regexp = ".*\\S.*", message = "Нельзя изменить содержимое комментария на пустое значение")
    private String content;

    @Schema(description = "Id новости к которой пишется комментарий", example = "1")
    @NotNull(message = "newsId является обязательным параметром")
    private Long newsId;
}
