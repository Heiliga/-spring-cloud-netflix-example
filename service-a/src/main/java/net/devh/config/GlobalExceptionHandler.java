package net.devh.config;

import net.devh.dto.JsonResponse;
import net.devh.dto.JsonResponseBuilder;
import net.devh.exception.CompareException;
import net.devh.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompareException.class)
    public JsonResponse<?> handle(CompareException e) {
        return JsonResponseBuilder.error(e.getMessage(), e.getGuid());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public JsonResponse<?> handle(ServiceException e) {
        return JsonResponseBuilder.error(e.getMessage(), e.getGuid());
    }
}