package com.backbase.assignment.kalah.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates an object error, that is, a global reason for rejecting an object.
 *
 * @author Jibran Tariq
 * @version 1.0
 * @see JsonInclude
 * @see Data
 * @see Builder
 */
@JsonInclude(Include.NON_NULL)
@Data
@Builder
public class ApiError {

  private String message;
}
