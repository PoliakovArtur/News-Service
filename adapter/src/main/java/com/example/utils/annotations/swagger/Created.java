package com.example.utils.annotations.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "201")
public @interface Created {
    @AliasFor(annotation = ApiResponse.class, attribute = "description")
    String description() default "Сущность создана";

    @AliasFor(annotation = ApiResponse.class, attribute = "content")
    Content content() default @Content;
}
