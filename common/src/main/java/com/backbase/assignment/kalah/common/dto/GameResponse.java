package com.backbase.assignment.kalah.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulates a {@link com.backbase.assignment.kalah.common.domain.Game} object when game is
 * create on specific server.
 *
 * @author Jibran Tariq
 * @version 1.0
 * @see JsonInclude
 * @see Data
 * @see Builder
 */
@Data
@Builder
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {

  private Long id;
  private String uri;
}
