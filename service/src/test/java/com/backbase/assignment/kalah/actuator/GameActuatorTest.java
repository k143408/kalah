package com.backbase.assignment.kalah.actuator;

import com.backbase.assignment.kalah.KalahApplicationTests;
import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.Move;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.exception.KalahException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** The type Game actuator test. */
public class GameActuatorTest extends KalahApplicationTests {

  @Autowired private GameActuator gameActuator;

  /** Test when actuator creates game. */
  @Test
  public void testWhenActuatorCreatesGame() {

    Game game = gameActuator.create(Long.valueOf(1));

    Assert.assertNotNull("Game instance should not be null", game);
    Assert.assertEquals("Id of Game instance should be equal to 1 ", Long.valueOf(1), game.getId());
  }

  /** Test when one move perform. */
  @Test
  public void testWhenOneMovePerform() {

    Game game = gameActuator.performMove(mockGame, 1);

    Assert.assertEquals("Id of game instance should be equal", mockGame.getId(), game.getId());

    Assert.assertEquals(
        "Move of game instance should be equal", new Move(1), game.getCurrentMove());
  }

  @Test(expected = KalahException.class)
  public void testWhenMoveIsIncorrect() {

    gameActuator.performMove(mockGame, 15);

  }

  @Test(expected = KalahException.class)
  public void testWhenMoveIsIncorrectOfSouthPlayer() {

    gameActuator.performMove(mockGame, 11);

  }

  @Test(expected = KalahException.class)
  public void testWhenMoveIsIncorrectOfNorthPlayer() {
    mockGame.setTakeTurnPlayer(NorthPlayer.builder().build());
    gameActuator.performMove(mockGame, 1);
  }

  /** Test when updated board is mapped to representable format. */
  @Test
  public void testWhenUpdatedBoardIsMappedToRepresentableFormat() {
    Map mockMap = new HashMap();
    for (int i = 0; i < 14; i++) {
      mockMap.put("" + (i + 1), "0");
    }

    Map map = gameActuator.mapGameBoardToMapObject(mockGame);

    Assert.assertEquals(
        "Value of 1 in map should be equals to resultant map ", mockMap.get("1"), map.get("1"));
  }
}
