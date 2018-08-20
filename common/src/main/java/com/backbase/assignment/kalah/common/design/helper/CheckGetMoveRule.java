package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.Game;

/**
 * Check if the player can take the role with the selected house. It can be the case when choosing a
 * home owned by another player, so such a move is not acceptable.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface CheckGetMoveRule {

  /**
   * Check move of player.
   *
   * @param game the game
   * @param selectedHouse the selected house
   * @return the boolean
   */
  default boolean checkMoveOfPlayer(Game game, int selectedHouse) {
    int storeIndex = game.getTakeTurnPlayer().storeIndex();
    return selectedHouse < storeIndex && selectedHouse >= storeIndex - Board.HOUSES_PER_PLAYER;
  }
}
