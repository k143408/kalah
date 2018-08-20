package com.backbase.assignment.kalah.common.design.helper;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.Player;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The action of this rule should be called once before the beginning of the game. All the houses of
 * the board are filled by {@link Board#STONES_PER_HOUSE} numbers excepting 2 stores, which should
 * be left empty at the beginning and find indexes of stores for each player to use it further for
 * having them empty while filling houses
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface InitGameRule {

  /**
   * Fill board at starting of game.
   *
   * @param game the game
   * @param storeIndexes the store indexes
   */
  default void fillBoardAtStartingOfGame(Game game, List<Integer> storeIndexes) {
    Board board = game.getBoard();
    if (!board.isFilled()) {
      int[] houses = board.getHouses();

      for (int i = 0; i < houses.length; i++) {
        if (!storeIndexes.contains(i)) {
          houses[i] = Board.STONES_PER_HOUSE;
        }
      }
      board.setFilled(Boolean.TRUE);
      game.setStarted(Boolean.TRUE);
    }
  }

  /**
   * Find indexes of store list.
   *
   * @param game the game
   * @return the list
   */
  default List<Integer> findIndexesOfStore(Game game) {
    List<Integer> indexes =
        game.getPlayers().stream().map(Player::storeIndex).collect(Collectors.toList());
    return indexes;
  }
}
