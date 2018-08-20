package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.Player;
import com.backbase.assignment.kalah.common.exception.UnableToFetchStonesException;
import java.util.stream.Collectors;

/**
 * Pull the stones from the house chosen and counterclockwise skipping the store if the number of
 * stones in it So large that you can have 2 rolls of sowing and will be choosing the house Also
 * implanted in each roll after the first one.
 *
 * <p>After sowing is finished, last sown index is kept for further steps of processing based on
 * Kalah rules
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface SowingStoneRule {

  /**
   * Perform sowing the stones.
   *
   * @param game {@link Game}
   * @param selectedHouse index of the house, which has been chosen by current player
   * @param opponentStoreIndex index of store cell of another player. needed to skip it while sowing
   */
  default void performSowingTheStones(Game game, int selectedHouse, Integer opponentStoreIndex) {
    int[] targetArray = game.getBoard().getHouses();

    // start sowing from next house
    int startPositionToMove = selectedHouse + 1;

    // move for the same number of houses as we have stones in house
    int stonesInHouse = targetArray[selectedHouse];

    if (stonesInHouse == 0) throw new UnableToFetchStonesException("There is no stone in house");
    // pull all the stones from the house
    targetArray[selectedHouse] = 0;

    // keep in mind where last sown stone is located for further steps
    int lastSownIndex = -1;
    // iterate in circular manner over array and put 1 stone into each next house
    for (int i = 0; i < stonesInHouse; i++) {
      lastSownIndex = (i + startPositionToMove) % targetArray.length;

      // skip opponent store and fill next house
      if (lastSownIndex == opponentStoreIndex) {
        lastSownIndex = (++i + startPositionToMove) % targetArray.length;
        // add 1 stone to avoid skipping one cell when we are jumping over opposite player's store
        stonesInHouse += 1;
      }
      targetArray[lastSownIndex] += 1;
    }

    game.getCurrentMove().setLastSownIndex(lastSownIndex);
  }

  /**
   * Find index of the store of another player
   *
   * @param game {@link Game}
   * @return store index of another player.
   */
  default Integer opponentStoreIndex(Game game) {
    return game.getPlayers()
        .stream()
        .map(Player::storeIndex)
        .filter(index -> game.getTakeTurnPlayer().storeIndex() != index)
        .collect(Collectors.toList())
        .get(0);
  }
}
