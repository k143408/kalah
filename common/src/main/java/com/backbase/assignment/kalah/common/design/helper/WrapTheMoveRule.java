package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Game;
import java.util.stream.Collectors;

/**
 * Prepare for the next round. Set the current jump as the previous one. Find another player to take
 * it.
 */
public interface WrapTheMoveRule {

  /**
   * Wrap current move.
   *
   * @param game the game
   */
  default void wrapCurrentMove(Game game) {
    game.setPreviousMove(game.getCurrentMove());
    game.setCurrentMove(null);
    game.setTakeTurnPlayer(
        game.getPlayers()
            .stream()
            .filter(player -> !player.equals(game.getTakeTurnPlayer()))
            .collect(Collectors.toList())
            .get(0));
  }
}
