package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.Player;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Find winner in case of finishing the game.
 *
 * <ul>
 *   <li>Calculate the stored value of each player and sort it according to the storage value.
 * </ul>
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface WinnerAnalyzerRule {

  /**
   * Seeking winner.
   *
   * @param game the game
   */
  default void seekWinner(Game game) {
    TreeMap<Integer, String> collectStoreValueFromPlayerName =
        game.getPlayers()
            .stream()
            .collect(
                Collectors.toMap(
                    player -> game.getBoard().getByIndex(player.storeIndex()),
                    Player::name,
                    (a, b) -> a,
                    TreeMap::new));

    if (collectStoreValueFromPlayerName.size() == 1) {
      game.setWinner("DRAW");
      game.setOver(Boolean.TRUE);
    } else {
      String winner = collectStoreValueFromPlayerName.pollLastEntry().getValue();
      game.setWinner(winner);
      game.setOver(Boolean.TRUE);
    }
  }
}
