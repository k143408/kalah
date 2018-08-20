package com.backbase.assignment.kalah.api;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.backbase.assignment.kalah.KalahApplicationTests;
import com.backbase.assignment.kalah.common.dto.GameResponse;
import com.backbase.assignment.kalah.common.dto.GameResponseWithStatus;
import com.backbase.assignment.kalah.service.GameService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/** The type Games controller test. */
public class GamesControllerTest extends KalahApplicationTests {

  @Autowired private GamesController gamesController;

  @MockBean private GameService gameService;

  /** Test when controller calls create game. */
@Test
  public void testWhenControllerCallsCreateGame() {
    GameResponse gameResponse = GameResponse.builder().id(Long.valueOf(1)).build();

    when(gameService.create()).thenReturn(gameResponse);

    GameResponse gameResponseFromController = gamesController.createGame();

    Assert.assertEquals(gameResponse.getId(), gameResponseFromController.getId());
  }

  /** Test when controller calls move method. */
@Test
  public void testWhenControllerCallsMoveMethod() {
    GameResponseWithStatus gameResponseWithStatus = GameResponseWithStatus.builder().id(Long.valueOf(1)).build();

    when(gameService.perform(anyLong(),anyInt())).thenReturn(gameResponseWithStatus);

    GameResponseWithStatus gameResponseWithStatusFromController = gamesController.move(Long.valueOf(1),1);

    Assert.assertEquals(gameResponseWithStatus.getId(), gameResponseWithStatusFromController.getId());
  }
}
