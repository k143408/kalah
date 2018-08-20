package com.backbase.assignment.kalah.common.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * This exception class for wrapping {@code NestedRuntimeException} with a root cause.
 *
 * <p>This class is to get extended with specific exceptions. This class is included nested
 * exception information; {@code printStackTrace} and other like methods will delegate to the
 * wrapped exception, if any.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class KalahException extends NestedRuntimeException {

  /**
   * Construct a {@code KalahException} with the specified detail message.
   *
   * @param msg the detail message
   */
  public KalahException(String msg) {
    super(msg);
  }

  /**
   * Construct a {@code KalahException} with the specified detail message and nested exception.
   *
   * @param msg the detail message
   * @param cause the nested exception
   */
  public KalahException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
