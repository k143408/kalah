package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Board;

/**
 * Clean up houses after summing them to store.
 *
 * @author Jibran Tariq
 * @version 1.0
 *
 */
public interface CleanHousesAfterMoveRemainingStoneRule {

  /**
   * Clean up houses after summing them to store.
   *
   * @param whoHasNonEmptyHousesStoreIndex the who has non empty houses store index
   * @param boardHouses the board houses
   */
  default void cleanUpHousesAfterSummingThemToStore(
      Integer whoHasNonEmptyHousesStoreIndex, int[] boardHouses) {
    for (int pos = whoHasNonEmptyHousesStoreIndex - Board.HOUSES_PER_PLAYER;
        pos < whoHasNonEmptyHousesStoreIndex;
        pos++) {
      boardHouses[pos] = 0;
    }
  }
}
