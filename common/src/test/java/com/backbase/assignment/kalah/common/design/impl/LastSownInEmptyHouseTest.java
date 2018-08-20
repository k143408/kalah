package com.backbase.assignment.kalah.common.design.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.Move;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.Player;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * The Unit Test of {@link LastSownInEmptyHouseRuleImpl}
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class LastSownInEmptyHouseTest {

  private Game game;

  private final InitializeBoardRuleImpl board = new InitializeBoardRuleImpl();
  private final SowStonesRuleImpl sowStones = new SowStonesRuleImpl();
  private final LastSownRuleImpl lastSownInPlayerStore = new LastSownRuleImpl();
  private final LastSownInEmptyHouseRuleImpl lastSownInEmptyHouse =
      new LastSownInEmptyHouseRuleImpl();

  /** Sets up. */
  @Before
  public void setUp() {
    game =
        new Game(
            Long.MIN_VALUE,
            Arrays.asList(SouthPlayer.builder().build(), NorthPlayer.builder().build()));
    board.implement(game);
    game.setCurrentMove(new Move(3));
    game.setTakeTurnPlayer(SouthPlayer.builder().build());
  }

  /** Test scenario when another player should take move. */
  @Test
  public void testScenarioWhenAnotherPlayerShouldTakeMove() {
    int[] houses = game.getBoard().getHouses();
    houses[3] = 13;
    sowStones.implement(game);

    lastSownInPlayerStore.implement(game);
    Move futurePreviousTurn = game.getCurrentMove();
    Player futurePreviousTakeTurnPlayer = game.getTakeTurnPlayer();
    lastSownInEmptyHouse.implement(game);

    int playerStoreValue = game.getBoard().getByIndex(futurePreviousTakeTurnPlayer.storeIndex());

    assertEquals(
        "store value should be 9 since we are capturing 1+7 from houses to 1 in the store",
        9,
        playerStoreValue);

    assertEquals(
        "Another player should take a move",
        NorthPlayer.builder().build(),
        game.getTakeTurnPlayer());
    assertEquals(0, houses[3]);
    assertEquals(0, houses[9]);
    assertNull("Current move should be null", game.getCurrentMove());
    assertEquals(
        "Current move should become previous move", futurePreviousTurn, game.getPreviousMove());
  }

  /** Test player cannot capture from another player house. */
  @Test
  public void testPlayerCannotCaptureFromAnotherPlayerHouse() {
    Move currentTurn = new Move(1);
    currentTurn.setLastSownIndex(12);
    game.setCurrentMove(currentTurn);

    lastSownInEmptyHouse.implement(game);

    assertEquals(
        "Another player should take a move",
        NorthPlayer.builder().build(),
        game.getTakeTurnPlayer());
    assertNull("Current move should be null", game.getCurrentMove());
    assertEquals("Current move should become previous move", currentTurn, game.getPreviousMove());
  }

  /** Test trying to capture with empty opposite house. */
  @Test
  public void testTryingToCaptureWithEmptyOppositeHouse() {
    int[] houses = game.getBoard().getHouses();
    houses[3] = 13;
    sowStones.implement(game);

    lastSownInPlayerStore.implement(game);
    Move futurePreviousTurn = game.getCurrentMove();
    Player futurePreviousTakeTurnPlayer = game.getTakeTurnPlayer();

    houses[9] = 0;

    lastSownInEmptyHouse.implement(game);

    int playerStoreValue = game.getBoard().getByIndex(futurePreviousTakeTurnPlayer.storeIndex());

    assertEquals("store value should be 1 since opposite house is empty", 1, playerStoreValue);

    assertEquals(
        "Another player should take a move",
        NorthPlayer.builder().build(),
        game.getTakeTurnPlayer());
    assertEquals(1, houses[3]);
    assertEquals(0, houses[9]);
    assertNull("Current move should be null", game.getCurrentMove());
    assertEquals(
        "Current move should become previous move", futurePreviousTurn, game.getPreviousMove());
  }
}
