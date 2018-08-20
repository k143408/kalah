package com.backbase.assignment.kalah.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulates a {@link com.backbase.assignment.kalah.common.domain.Game} object when move is
 * performed on specific house.
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
public class GameResponseWithStatus {
  private Long id;
  private String uri;
  private Map<String, String> status;
}
