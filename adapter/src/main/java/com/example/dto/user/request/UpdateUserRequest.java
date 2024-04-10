package com.example.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {
    @Schema(description = "Имя пользователя", example = "John Doe")
    @Pattern(regexp = ".*\\S.*", message = "Нельзя изменить имя пользователя на пустое значение")
    private String name;

    @Schema(description = "Возраст", example = "22")
    @Positive(message = "Возраст не может быть меньше 1")
    private Integer age;

    @Schema(description = "Почтовый адрес", example = "example.example@example.ru")
    @Pattern(regexp = "([a-z0-9]+\\.)*[a-z0-9]+@([a-z]+\\.)+[a-z]{2,3}", message = "Невалидное значение email")
    private String email;

    @Schema(description = "Обо мне", example = "Люблю путешествовать")
    private String aboutMe;
}
