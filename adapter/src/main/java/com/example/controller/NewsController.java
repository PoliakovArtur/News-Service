package com.example.controller;

import com.example.dto.news.request.UpdateNewsRequest;
import com.example.dto.news.response.FullNewsResponse;
import com.example.dto.news.request.CreateNewsRequest;
import com.example.dto.news.response.ShortNewsResponse;
import com.example.dto.news.response.UpdateNewsResponse;
import com.example.facade.NewsFacade;
import com.example.filter.impl.NewsFilter;
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
@RequestMapping("${endpoint.news}")
@RequiredArgsConstructor
@Tag(name = "Новости", description = "Получение/создание/редактирование новостей")
public class NewsController {

    private final NewsFacade facade;

    @Operation(
            summary = "Получение новостей постранично",
            description = "Получение новостей постранично с возможностью фильтрации и сортировки"
    )
    @Ok @BadRequest @InternalServerError
    @GetMapping
    public List<ShortNewsResponse> findAll(@Valid NewsFilter filter) {
        return facade.findAll(filter);
    }

    @Operation(
            summary = "Получение новости",
            description = "Получение новости по id со всеми комментариями к ней"
    )
    @Ok @NotFound
    @BadRequest
    @InternalServerError
    @GetMapping("/{id}")
    public FullNewsResponse findById(@PathVariable Long id) {
        return facade.findById(id);
    }

    @Operation(
            summary = "Создание новости",
            description = "Создание новости. В ответ приходит информация о созданной новости"
    )
    @Created
    @NotFound @BadRequest @InternalServerError
    @PostMapping
    public ResponseEntity<UpdateNewsResponse> save(@RequestBody @Valid CreateNewsRequest body) {
        return ResponseEntity.status(CREATED).body(facade.save(body));
    }

    @Operation(
            summary = "Редактирование новости",
            description = "Редактирование новости по id. Редактирование разрешается только пользователю, который создал новость"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @PutMapping("/{id}")
    public UpdateNewsResponse updateById(@PathVariable Long id,
                                         @RequestBody @Valid UpdateNewsRequest body) {
        return facade.updateById(id, body);
    }

    @Operation(
            summary = "Удаление новости",
            description = "Удаление новости по id. Удаление разрешается только пользователю, создавшему новость"
    )
    @NoContent
    @NotFound @InternalServerError
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id,
                                           @RequestParam Long userId) {
        facade.deleteById(userId, id);
        return ResponseEntity.noContent().build();
    }
}
