package com.backbase.assignment.kalah.common.domain.player;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * North player is the extension of {@link Player} class.
 *
 * <p>House's count is getting started from 0 index so this player is located at the top, it have a
 * index in the 13th index.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class NorthPlayer implements Player {

  /** The constant INDEX_NORTH. */
  private static final int INDEX_NORTH = 13;

  /** The constant PLAYER_NAME. */
  private static final String PLAYER_NAME = "north";

  @Override
  public String name() {
    return PLAYER_NAME;
  }

  @Override
  public int storeIndex() {
    return INDEX_NORTH;
  }
}
