package com.example.controller;

import com.example.dto.user.request.UpdateUserDto;
import com.example.filter.impl.UserFilter;
import com.example.dto.user.request.CreateUserDto;
import com.example.dto.user.response.UserInfoDto;
import com.example.facade.UserFacade;
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
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${endpoint.user}")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Получение/создание/редактирование пользователей")
public class UserController {

    private final UserFacade facade;

    @Operation(
            summary = "Постраничное получение пользователей",
            description = "Получение пользователей постранично с передачей номера и размера страницы"
    )
    @Ok @BadRequest @InternalServerError
    @GetMapping
    public List<UserInfoDto> findAll(@Valid UserFilter filter) {
        return facade.findAll(filter);
    }

    @Operation(
            summary = "Получение одного пользователя",
            description = "Получение одного пользователя по id"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @GetMapping("/{id}")
    public UserInfoDto findById(@PathVariable Long id) {
        return facade.findById(id);
    }

    @Operation(
            summary = "Создание пользователя",
            description = "Создание нового пользователя согласно переданным данным"
    )
    @Created @BadRequest @InternalServerError
    @PostMapping
    public ResponseEntity<UserInfoDto> save(@RequestBody @Valid CreateUserDto body) {
        return ResponseEntity.status(CREATED).body(facade.save(body));
    }

    @Operation(
            summary = "Редактирование пользователя",
            description = "Редактирование данных пользователя по id"
    )
    @Ok @NotFound @BadRequest @InternalServerError
    @PutMapping("/{id}")
    public UserInfoDto updateById(@PathVariable Long id,
                                  @RequestBody @Valid UpdateUserDto body) {
        return facade.updateById(id, body);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Удаление пользователя по id"
    )
    @NoContent @NotFound @InternalServerError
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id) {
        facade.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
