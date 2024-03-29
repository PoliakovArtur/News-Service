package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class UpdateDto {
    @Schema(description = "Id пользователя, создавшего ресурс", example = "1")
    @NotNull(message = "userId является обязательным параметром")
    private Long userId;
}
