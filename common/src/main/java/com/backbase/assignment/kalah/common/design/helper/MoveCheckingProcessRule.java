package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.Player;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This will analyze both players individually while one of them has all the empty houses on the
 * blackboard.
 *
 * <ul>
 *   <li>If Therefore, stones are transferred from the homes of another player to his shop.
 *   <li>After that, the houses are set To zero
 *   <li>If one has all empty houses, the game must end, otherwise, the game must Progress.
 * </ul>
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface MoveCheckingProcessRule extends CheckEmptyAllHousesRule, MoveRemainingStonesRule {

  /**
   * Analysing move.
   *
   * @param game {@link Game} * @return true if game should be finished, otherwise false
   * @return the boolean
   */
  default boolean analyseMove(Game game) {

    List<Integer> sortedStoreIndexes =
        game.getPlayers().stream().map(Player::storeIndex).sorted().collect(Collectors.toList());

    Integer southPlayerStoreIndex = sortedStoreIndexes.get(0);
    Integer northPlayerStoreIndex = sortedStoreIndexes.get(1);

    // Checking both players store index, if stones are transferred from the homes of another player
    // to his shop.
    return checkWhetherPlayerHasAllHousesEmpty(game, southPlayerStoreIndex)
        ? moveRemainingStonesToAnotherPlayerStore(game, northPlayerStoreIndex)
        : checkWhetherPlayerHasAllHousesEmpty(game, northPlayerStoreIndex)
            ? moveRemainingStonesToAnotherPlayerStore(game, southPlayerStoreIndex)
            : Boolean.FALSE;
  }
}
