package com.backbase.assignment.kalah.api;

import com.backbase.assignment.kalah.common.dto.GameResponse;
import com.backbase.assignment.kalah.common.dto.GameResponseWithStatus;
import com.backbase.assignment.kalah.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base {@link com.backbase.assignment.kalah.common.domain.Game} Controller, that carry this
 * annotation are treated as controllers where {@link RequestMapping @RequestMapping} methods assume
 * by default.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@RestController
@Slf4j
@RequestMapping(
    value = "games",
    consumes = {MediaType.APPLICATION_JSON_VALUE})
public class GamesController {

  @Autowired private GameService gameService;

  /**
   * Post mapping of game end-point in which indicates to get game created.
   *
   * @see GameResponse
   * @see GameService
   * @return the game response
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameResponse createGame() {
    return gameService.create();
  }

  /**
   * Put mapping of game end-point in which pit is sent in the game and get response with status.
   *
   * @see GameResponseWithStatus
   * @see GameService
   * @param gameId Path variable of the game id
   * @param pitId Path variable of pit id
   * @return the game response with status
   */
  @PutMapping("{gameId}/pits/{pitId}")
  public GameResponseWithStatus move(
      @PathVariable("gameId") Long gameId, @PathVariable("pitId") Integer pitId) {
    return gameService.perform(gameId, pitId);
  }
}
