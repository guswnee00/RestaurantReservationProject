package org.zerobase.restaurantreservationproject.global.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected CustomExceptionResponse handleCustomException(CustomException exception) {
        return new CustomExceptionResponse(exception.getCustomErrorCode());
    }
}
