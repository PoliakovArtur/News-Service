package com.example.handler;

import com.example.dto.ResponseMessageDto;
import com.example.exception.BadRequestException;
import com.example.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessageDto> handleNotFoundException(NotFoundException ex) {
        log.info(ex.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(new ResponseMessageDto(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseMessageDto> handleBadRequestException(BadRequestException ex) {
        log.info(ex.getMessage());
        return ResponseEntity.badRequest().body(new ResponseMessageDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.info(message);
        return ResponseEntity.badRequest().body(new ResponseMessageDto(message));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseMessageDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String message = "Некорректное значение параметра";
        log.info(message);
        return ResponseEntity.badRequest().body(new ResponseMessageDto(message));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ResponseMessageDto> handlePropertyReferenceException(PropertyReferenceException ex) {
        String message = "Ошибка в параметрах сортировки";
        log.info(message);
        return ResponseEntity.badRequest().body(new ResponseMessageDto(message));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseMessageDto> handleServerErrors(Throwable throwable) {
        log.warn(throwable.getMessage());
        return ResponseEntity.internalServerError().body(new ResponseMessageDto("Внутренняя ошибка сервера"));
    }
}
