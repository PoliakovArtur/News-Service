package com.example.controller;

import com.example.dto.category.request.UpdateCategoryRequest;
import com.example.dto.category.response.FullCategoryResponse;
import com.example.dto.category.request.CreateCategoryRequest;
import com.example.dto.category.response.ShortCategoryResponse;
import com.example.facade.CategoryFacade;
import com.example.filter.impl.CategoryFilter;
import com.example.utils.annotations.swagger.BadRequest;
import com.example.utils.annotations.swagger.Created;
import com.example.utils.annotations.swagger.InternalServerError;
import com.example.utils.annotations.swagger.NoContent;
import com.example.utils.annotations.swagger.NotFound;
import com.example.utils.annotations.swagger.Ok;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${endpoint.category}")
@RequiredArgsConstructor
@Tag(name = "Категории", description = "Получение/создание/редактирование категорий новостей")
public class CategoryController {

    private final CategoryFacade facade;

    @Operation(
            summary = "Постраничное получение категорий",
            description = "Получение категорий новостей постранично с передачей номера и размера страницы"
    )
    @Ok @BadRequest @InternalServerError
    @GetMapping
    public List<ShortCategoryResponse> findAll(@Valid CategoryFilter filter) {
        return facade.findAll(filter);
    }

    @Operation(
            summary = "Получение категории новостей",
            description = "Получение подробной информации о категории новостей по id"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @GetMapping("/{id}")
    public FullCategoryResponse findById(@PathVariable Long id) {
        return facade.findById(id);
    }

    @Operation(
            summary = "Создание категории новостей",
            description = "Создание категории новостей. В ответе возвращается созданная категория"
    )
    @Created @NotFound @BadRequest @InternalServerError
    @PostMapping
    public ResponseEntity<ShortCategoryResponse> save(@RequestBody @Valid CreateCategoryRequest body) {
        return ResponseEntity.status(CREATED).body(facade.save(body));
    }

    @Operation(
            summary = "Редактирование категории новостей",
            description = "Редактирование категории новостей по id. Редактирование разрешается только пользователю, который создал категорию"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @PutMapping("/{id}")
    public ShortCategoryResponse updateById(@PathVariable Long id,
                                            @RequestBody @Valid UpdateCategoryRequest dto) {
        return facade.updateById(id, dto);
    }

    @Operation(
            summary = "Удаление категории новостей",
            description = "Удаление категории новостей по id. Удаление разрешается только пользователю, создавшему категорию"
    )
    @NoContent @BadRequest @NotFound @InternalServerError
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id,
                                           @RequestParam Long userId) {
        facade.deleteById(userId, id);
        return ResponseEntity.noContent().build();
    }
}
