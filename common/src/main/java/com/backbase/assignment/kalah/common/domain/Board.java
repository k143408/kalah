package com.backbase.assignment.kalah.common.domain;

import com.backbase.assignment.kalah.common.exception.UnableToFetchStonesException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code Board} class contains state of the art for all homes and stores. From index, it is
 * able to pull the value of stores.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Builder
public class Board {

  /** The Houses. */
  @Getter @Builder.Default private final int[] houses = new int[14];

  /** The Filled. */
  @Setter @Getter @Builder.Default private boolean filled = Boolean.FALSE;

  /** The constant STONES_PER_HOUSE. */
  public static final int STONES_PER_HOUSE = 6;

  /** The constant HOUSES_PER_PLAYER. */
  public static final int HOUSES_PER_PLAYER = 6;

  /**
   * Get number of stones in house or store by index
   *
   * @param index index to the house/store
   * @return number of stones inside
   */
  public int getByIndex(int index) {
    if (index >= houses.length || index < 0) {
      throw new UnableToFetchStonesException("Unable to get element from index " + index);
    }
    return houses[index];
  }
}
