package com.backbase.assignment.kalah.common.exception;

import com.backbase.assignment.kalah.common.domain.Board;

/**
 * Specific {@code UnableToFetchStonesException} class is extended from {@link KalahException} class
 * for wrapping the runtime exception when value is empty from houses array in the {@link Board}**
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class UnableToFetchStonesException extends KalahException {

  /**
   * Instantiates a new Unable to fetch stones exception.
   *
   * @param message the message
   */
  public UnableToFetchStonesException(String message) {
    super(message);
  }
}
