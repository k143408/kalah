package com.backbase.assignment.kalah.api.handler;

import com.backbase.assignment.kalah.KalahApplicationTests;
import com.backbase.assignment.kalah.common.dto.ApiError;
import com.backbase.assignment.kalah.common.exception.KalahException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** The type Api exception handler test. */
public class ApiExceptionHandlerTest extends KalahApplicationTests {

  @Autowired private ApiExceptionHandler apiExceptionHandler;

  /** Test when kalah exception occurs. */
  @Test
  public void testWhenKalahExceptionOccurs() {

    KalahException mockKalahException = new KalahException("Mock Exception");
    ResponseEntity<ApiError> apiErrorResponseEntity =
        apiExceptionHandler.handleKalahException(mockKalahException);

    Assert.assertEquals(
        "Http Status should be equals to  Bad Request ",
        HttpStatus.BAD_REQUEST,
        apiErrorResponseEntity.getStatusCode());

    Assert.assertEquals(
        "Body should be equal to Mock Exception",
        "Mock Exception",
        apiErrorResponseEntity.getBody().getMessage());
  }
}
