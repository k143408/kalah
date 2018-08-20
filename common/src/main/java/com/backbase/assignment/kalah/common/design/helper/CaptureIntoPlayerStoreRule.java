package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.Player;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface Capture into player store.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface CaptureIntoPlayerStoreRule extends CheckCurrentPlayerLastSownHouseRule {

  /**
   * Capture opposite and current house into player store.
   *
   * @param game the game
   * @param lastSownIndex the last sown index
   */
  default void captureOppositeAndCurrentHouseIntoPlayerStore(Game game, int lastSownIndex) {
    List<Integer> storeIndexes =
        game.getPlayers().stream().map(Player::storeIndex).collect(Collectors.toList());

    if (!storeIndexes.contains(lastSownIndex)
        && lastSownHouseIsCurrentPlayerHouse(lastSownIndex, game.getTakeTurnPlayer())) {
      int lastSownHouseValue = game.getBoard().getByIndex(lastSownIndex);

      if (lastSownHouseValue == 1) {
        int oppositeHouseIndex = Math.abs(lastSownIndex - 12);
        int oppositeHouseValue = game.getBoard().getByIndex(oppositeHouseIndex);
        if (oppositeHouseValue > 0) {
          int[] houses = game.getBoard().getHouses();
          houses[game.getTakeTurnPlayer().storeIndex()] += oppositeHouseValue + lastSownHouseValue;
          houses[oppositeHouseIndex] = 0;
          houses[lastSownIndex] = 0;
        }
      }
    }
  }
}
