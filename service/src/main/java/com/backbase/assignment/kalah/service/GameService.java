package com.backbase.assignment.kalah.service;

import com.backbase.assignment.kalah.common.dto.GameResponse;
import com.backbase.assignment.kalah.common.dto.GameResponseWithStatus;

/**
 * This class is responsible to process their incoming requests to create games and handle the move
 * of games.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface GameService {

  /**
   * Create game object. First generate unique ID for game object assoicate it with game object and
   * save it into Cache and map it into {@link GameResponse} object.
   *
   * @return the game response
   */
  GameResponse create();

  /**
   * Perform move from the selected house index on the specific game. It takes game id, fetch into
   * {@link com.backbase.assignment.kalah.cache.Cache} object if it is not there then {@link
   * com.backbase.assignment.kalah.common.exception.KalahException} will occur. If {@code Game} is
   * found then selectedHouse will be checked move of the selected player. If selected move is
   * not associated with that player then {@code KalahException} for incorrect selectedHouseNumber.
   * If everything is fine then games rules will be applied on selected game. stage of Game will be
   * updated and map into {@link GameResponseWithStatus} object.
   *
   * @param gameId the game id
   * @param pitId the pit id
   * @return the game response with status
   */
  GameResponseWithStatus perform(Long gameId, Integer pitId);
}
