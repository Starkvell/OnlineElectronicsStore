package spb.nicetu.OnlineElectronicsStore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.NotFoundException;
import spb.nicetu.OnlineElectronicsStore.dto.NotFoundExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<NotFoundExceptionResponse> handleNotFoundException(NotFoundException notFoundException) {
        NotFoundExceptionResponse notFoundExceptionResponse = new NotFoundExceptionResponse(
                notFoundException.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(notFoundExceptionResponse, HttpStatus.NOT_FOUND);
    }
}
