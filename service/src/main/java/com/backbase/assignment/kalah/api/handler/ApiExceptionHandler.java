package com.backbase.assignment.kalah.api.handler;

import com.backbase.assignment.kalah.common.dto.ApiError;
import com.backbase.assignment.kalah.common.exception.KalahException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A convenient base class for {@link org.springframework.web.bind.annotation.RestController}
 * classes that wish to provide centralized exception handling across all {@code @RequestMapping}
 * methods through {@code @ExceptionHandler} methods.
 *
 * @author Jibran Tariq
 * @version 1.0
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handle kalah application response entity.
   *
   * @param ex KalahException exception
   * @return the response entity {@link ResponseEntity}
   */
  @ExceptionHandler({KalahException.class})
  public ResponseEntity<ApiError> handleKalahException(KalahException ex) {
    ApiError objectError = ApiError.builder().message(ex.getMessage()).build();
    return new ResponseEntity<>(objectError, HttpStatus.BAD_REQUEST);
  }
}
