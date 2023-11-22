package com.workshopmongo.resources.exception;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.workshopmongo.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception,
      HttpServletRequest request) {
    StandardError error = new StandardError();
    error.setTimestamp(Instant.now());
    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage("");
    error.setError(exception.getMessage());
    error.setPath(request.getRequestURI());
    return ResponseEntity.status(error.getStatus()).body(error);
  }

}
