package com.backbase.assignment.kalah.common.domain.player;

/**
 * {@code Player} class which is required to get common attributes of associated Players.
 *
 * @author Jibran Tariq
 * @version 1.0
 *
 */
public interface Player {

  /**
   * Name of Player.
   *
   * @return the name of Player
   */
  String name();

  /**
   * Position index of Player's home.
   *
   * @return the index.
   */
  int storeIndex();
}
