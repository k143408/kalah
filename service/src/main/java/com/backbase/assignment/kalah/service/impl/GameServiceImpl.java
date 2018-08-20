package com.backbase.assignment.kalah.service.impl;

import com.backbase.assignment.kalah.KalahApplication;
import com.backbase.assignment.kalah.actuator.GameActuator;
import com.backbase.assignment.kalah.cache.Cache;
import com.backbase.assignment.kalah.common.dto.GameResponse;
import com.backbase.assignment.kalah.common.dto.GameResponseWithStatus;
import com.backbase.assignment.kalah.common.exception.KalahException;
import com.backbase.assignment.kalah.common.util.ServerInfo;
import com.backbase.assignment.kalah.service.GameService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** The Implementation of {@link GameService}
 *
 * @author Jibran Tariq
 * @version 1.0
 **/
@Service
public class GameServiceImpl implements GameService {

  @Autowired private ServerInfo serverInfo;
  @Autowired private GameActuator gameActuator;
  @Autowired private Cache cache;

  @Override
  public GameResponse create() {

    return Optional.ofNullable(KalahApplication.ID_GENERATOR.getAndIncrement())
        .map(gameActuator::create)
        .map(
            game -> {
              cache.addGame(game.getId(), game);
              return game.getId();
            })
        .map(id -> GameResponse.builder().id(id).uri(serverInfo.getGameUri(id)).build())
        .get();
  }

  @Override
  public GameResponseWithStatus perform(Long gameId, Integer pitId) {

    return Optional.ofNullable(gameId)
        .map(id -> cache.getGame(id))
        .map(game -> gameActuator.performMove(game, pitId - 1))
        .map(
            game ->
                GameResponseWithStatus.builder()
                    .uri(serverInfo.getGameUri(gameId))
                    .id(gameId)
                    .status(gameActuator.mapGameBoardToMapObject(game))
                    .build())
        .orElseThrow(() -> new KalahException("No Game exists"));
  }
}
