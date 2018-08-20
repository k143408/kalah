package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.Game;
import java.util.Arrays;

/**
 * Find all house players and make sure the sum of them is 0.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface CheckEmptyAllHousesRule {

  /**
   * Check whether player has all houses empty boolean.
   *
   * @param game {@link Game}
   * @param storeIndex index of the store cell for player
   * @return true if all the houses are empty
   */
  default boolean checkWhetherPlayerHasAllHousesEmpty(Game game, Integer storeIndex) {
    return Arrays.stream(
                Arrays.copyOfRange(
                    game.getBoard().getHouses(), storeIndex - Board.HOUSES_PER_PLAYER, storeIndex))
            .sum()
        == 0;
  }
}
