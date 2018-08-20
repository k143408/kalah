package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.player.Player;

/**
 * The interface Check current player last sown house.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface CheckCurrentPlayerLastSownHouseRule {

  /**
   * Last sown house is current player house boolean.
   *
   * @param lastSownIndex the last sown index
   * @param takeTurnPlayer the take move player
   * @return the boolean
   */
  default boolean lastSownHouseIsCurrentPlayerHouse(int lastSownIndex, Player takeTurnPlayer) {
    int storeIndex = takeTurnPlayer.storeIndex();
    return lastSownIndex < storeIndex && lastSownIndex >= storeIndex - Board.HOUSES_PER_PLAYER;
  }
}
