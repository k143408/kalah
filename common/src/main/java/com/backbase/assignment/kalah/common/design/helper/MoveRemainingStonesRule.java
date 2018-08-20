package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.Game;
import java.util.Arrays;

/**
 * Move remaining stones from the houses of another player to his store and clean houses up.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface MoveRemainingStonesRule extends CleanHousesAfterMoveRemainingStoneRule {

  /**
   * Move remaining stones from the houses of another player to his store and clean houses up
   *
   * @param game {@link Game}
   * @param whoHasNonEmptyHousesStoreIndex index to cell of {@link Board#houses} for store of
   *     another player (who doesn't have all the empty houses)
   * @return the boolean
   */
  default Boolean moveRemainingStonesToAnotherPlayerStore(
      Game game, Integer whoHasNonEmptyHousesStoreIndex) {
    int[] boardHouses = game.getBoard().getHouses();

    int[] playerHouses =
        Arrays.copyOfRange(
            boardHouses,
            whoHasNonEmptyHousesStoreIndex - Board.HOUSES_PER_PLAYER,
            whoHasNonEmptyHousesStoreIndex);

    int sumOfStonesInNonEmptyHousesForAnotherPlayer = Arrays.stream(playerHouses).sum();
    boardHouses[whoHasNonEmptyHousesStoreIndex] += sumOfStonesInNonEmptyHousesForAnotherPlayer;

    cleanUpHousesAfterSummingThemToStore(whoHasNonEmptyHousesStoreIndex, boardHouses);

    return Boolean.TRUE;
  }
}
