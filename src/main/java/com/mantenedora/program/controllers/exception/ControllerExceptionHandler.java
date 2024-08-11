package com.mantenedora.program.controllers.exception;

import com.mantenedora.program.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandError> ObjetoNaoEncontrado(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandError error = new StandError(System.currentTimeMillis(), status.value(), "Objeto não encontrado!", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
