package com.backbase.assignment.kalah.common.domain.player;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * South player is the extension of {@link Player} class.
 *
 * <p>House's count is getting started from 0 index so this player is located at the bottom, it have
 * a index in the 6th index.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class SouthPlayer implements Player {

  /** The constant INDEX_NORTH. */
  private static final int INDEX_SOUTH = 6;

  /** The constant PLAYER_NAME. */
  private static final String PLAYER_NAME = "south";

  @Override
  public String name() {
    return PLAYER_NAME;
  }

  @Override
  public int storeIndex() {
    return INDEX_SOUTH;
  }
}
