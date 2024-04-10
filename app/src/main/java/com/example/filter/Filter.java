package com.example.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public abstract class Filter {

    @Schema(description = "Количество записей на странице", example = "5")
    @NotNull(message = "pageSize является обязательным параметром")
    @Positive(message = "Размер страницы должен быть больше 0")
    private int pageSize;

    @Schema(description = "Номер страницы", example = "0")
    @NotNull(message = "pageNumber является обязательным параметром")
    @PositiveOrZero(message = "Номер страницы не может быть меньше 0")
    private int pageNumber;

    @Schema(description = "Параметр сортировки", example = "id")
    private String sort;

    @Schema(description = "Сортировка в обратном порядке", example = "false")
    private Boolean desc = false;
}
