package com.example.controller;

import com.example.dto.comment.request.CreateCommentRequest;
import com.example.dto.comment.request.UpdateCommentRequest;
import com.example.dto.comment.response.CommentResponse;
import com.example.facade.CommentFacade;
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
@RequestMapping("${endpoint.comment}")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Получение/создание/редактирование комментариев")
public class CommentController {

    private final CommentFacade facade;

    @Operation(
            summary = "Получение комментариев",
            description = "Получение всех комментариев к определенной новости"
    )
    @Ok @BadRequest @NotFound @InternalServerError
    @GetMapping
    public List<CommentResponse> findAll(@RequestParam Long newsId) {
        return facade.findByNews(newsId);
    }

    @Operation(
            summary = "Получение комментария",
            description = "Получение комментария по id"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @GetMapping("/{id}")
    public CommentResponse findById(@PathVariable Long id) {
        return facade.findById(id);
    }

    @Operation(
            summary = "Создание комментария",
            description = "Создание комментария. В ответ приходит информация о созданном комментарии"
    )
    @Created
    @NotFound @BadRequest @InternalServerError
    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody @Valid CreateCommentRequest body) {
        return ResponseEntity.status(CREATED).body(facade.save(body));
    }

    @Operation(
            summary = "Редактирование комментария",
            description = "Редактирование комментария по id. Редактирование разрешается только пользователю, который создал комментарий"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @PutMapping("/{id}")
    public CommentResponse updateById(@PathVariable Long id,
                                      @RequestBody @Valid UpdateCommentRequest body) {
        return facade.updateById(id, body);
    }

    @Operation(
            summary = "Удаление комментария",
            description = "Удаление комментария по id. Удаление разрешается только пользователю, создавшему комментарий"
    )
    @NoContent @BadRequest @NotFound @InternalServerError
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id,
                                           @RequestParam Long userId) {
        facade.deleteById(userId, id);
        return ResponseEntity.noContent().build();
    }
}
