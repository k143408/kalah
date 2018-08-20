package com.backbase.assignment.kalah.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.backbase.assignment.kalah.KalahApplicationTests;
import com.backbase.assignment.kalah.actuator.GameActuator;
import com.backbase.assignment.kalah.cache.Cache;
import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import com.backbase.assignment.kalah.common.dto.GameResponse;
import com.backbase.assignment.kalah.common.dto.GameResponseWithStatus;
import com.backbase.assignment.kalah.common.exception.KalahException;
import com.backbase.assignment.kalah.common.util.ServerInfo;
import com.backbase.assignment.kalah.service.GameService;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * The Game gameService impl test.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class GameServiceImplTest extends KalahApplicationTests {

  @MockBean private ServerInfo serverInfo;
  @MockBean private GameActuator gameActuator;
  @MockBean private Cache cache;

  @Autowired private GameService gameService;


  /** Test when game is created. */
  @Test
  public void testWhenGameIsCreated() {

    given(gameActuator.create(anyLong())).willReturn(mockGame);

    doNothing().when(cache).addGame(anyLong(), any(Game.class));

    given(serverInfo.getGameUri(anyLong())).willReturn(mockSeverInfo);

    GameResponse gameResponse = gameService.create();

    Assert.assertEquals(
        "Game Response Id should be equal to 1", gameResponse.getId(), Long.valueOf(1));

    Assert.assertEquals(
        "Game Response Uri should be equal to given mockSeverInfo",
        gameResponse.getUri(),
        mockSeverInfo);
  }

  /** Test when move is performed on existing game. */
  @Test
  public void testWhenMoveIsPerformedOnExistingGame() {

    given(cache.getGame(anyLong())).willReturn(mockGame);

    given(gameActuator.performMove(any(Game.class), anyInt())).willReturn(mockGame);

    given(serverInfo.getGameUri(anyLong())).willReturn(mockSeverInfo);

    given(gameActuator.mapGameBoardToMapObject(any(Game.class))).willReturn(null);

    GameResponseWithStatus gameResponseWithStatus = gameService.perform(Long.valueOf(1), 1);

    Assert.assertEquals(
        "Game Response Uri should be equal to given mockSeverInfo",
        gameResponseWithStatus.getUri(),
        mockSeverInfo);

    Assert.assertEquals(
        "Game Response Id should be equal to 1", gameResponseWithStatus.getId(), Long.valueOf(1));

    Assert.assertNull(
        "Game Response status should be equal to null", gameResponseWithStatus.getStatus());
  }

  /** Test when game instance is not there. */
  @Test(expected = KalahException.class)
  public void testWhenGameInstanceIsNotThere() {

    given(cache.getGame(anyLong())).willReturn(null);

    gameService.perform(Long.valueOf(1), 1);
  }
}
