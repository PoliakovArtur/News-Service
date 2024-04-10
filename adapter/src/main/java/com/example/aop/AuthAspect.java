package com.example.aop;

import com.example.dto.UpdateByUserRequest;
import com.example.exception.BadRequestException;
import com.example.service.CategoryService;
import com.example.service.CommentService;
import com.example.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.AspectException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.text.MessageFormat.format;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class AuthAspect {

    private final CategoryService categoryService;
    private final CommentService commentService;
    private NewsService newsService;

    @Before("execution(* com.example.facade.*.updateById(..)) && args(id, updateDto)")
    public void authEditing(JoinPoint joinPoint, Long id, UpdateByUserRequest updateDto) {
        Long userIdFromDto = updateDto.getUserId();
        log.info("Вызван метод {}. Аутентификация пользователя с id {} на изменение ресурса",
                joinPoint.getSignature().getName(), userIdFromDto);

        Long userIdFromDB = getUserIdFromDB(id, updateDto.getClass().getName());
        if(!userIdFromDB.equals(userIdFromDto)) {
            log.info("Аутентификация прошла неудачно");
            throw new BadRequestException(
                    format("Пользователь с id {0} не может редактировать этот ресурс, т.к. у него недостаточно прав",
                            userIdFromDto, id));
        }
        log.info("Аутентификация прошла успешно");
    }

    @Before("execution(* com.example.facade.*.deleteById(..)) && args(userId, id)")
    public void authDeleting(JoinPoint joinPoint, Long userId, Long id) {
        Signature signature = joinPoint.getSignature();
        log.info("Вызван метод {}. Аутентификация пользователя с id {} на удаление ресурса",
                signature.getName(), userId);

        Long userIdFromDB = getUserIdFromDB(id, signature.getDeclaringType().getSimpleName().replace("Facade", ""));
        if(!userIdFromDB.equals(userId)) {
            log.info("Аутентификация прошла неудачно");
            throw new BadRequestException(
                    format("Пользователь с id {0} не может удалить этот ресурс, т.к. у него недостаточно прав",
                            userId, id));
        }
        log.info("Аутентификация прошла успешно");
    }

    private Long getUserIdFromDB(Long id, String entityClass) {
        return switch(entityClass) {
            case "Category" -> categoryService.findById(id).getUser().getId();
            case "News" -> newsService.findById(id).getUser().getId();
            case "Comment" -> commentService.findById(id).getUser().getId();
            default -> throw new AspectException(format("Для сущности {0} в AuthAspect нет соответствующего сервиса"));
        };
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
