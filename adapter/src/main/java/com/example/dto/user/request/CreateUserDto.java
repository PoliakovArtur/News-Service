package com.example.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateUserDto {
    @Schema(description = "Имя пользователя", example = "John Doe")
    @NotNull(message = "name является обязательным параметром")
    @NotBlank(message = "Нельзя создать пользователя с пустым именем")
    private String name;

    @Schema(description = "Возраст", example = "22")
    @Positive(message = "Возраст не может быть меньше 1")
    private Integer age;

    @Schema(description = "Почтовый адрес", example = "example.example@example.ru")
    @NotNull(message = "email является обязательным параметром")
    @Pattern(regexp = "([a-z0-9]+\\.)*[a-z0-9]+@([a-z]+\\.)+[a-z]{2,3}")
    private String email;

    @Schema(description = "Обо мне", example = "Люблю путешествовать")
    private String aboutMe;
}
