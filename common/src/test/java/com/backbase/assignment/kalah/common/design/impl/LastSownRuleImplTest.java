package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.Move;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.Player;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The Unit Test of LastSownRuleImpl
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class LastSownRuleImplTest {

  private Game game;

  private final InitializeBoardRuleImpl board = new InitializeBoardRuleImpl();
  private final SowStonesRuleImpl sowStones = new SowStonesRuleImpl();
  private final LastSownRuleImpl lastSownInPlayerStore = new LastSownRuleImpl();

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

  /** Test when last sown in the store. */
  @Test
  public void testWhenLastSownInTheStore() {
    game.getBoard().getHouses()[3] = 3;
    sowStones.implement(game);
    Move currentTurn = game.getCurrentMove();
    Player takeTurnPlayer = game.getTakeTurnPlayer();
    lastSownInPlayerStore.implement(game);

    Assert.assertEquals(
        "take-turn-player should be the same when the last stone is put into the player's store",
        takeTurnPlayer,
        game.getTakeTurnPlayer());
    Assert.assertNull(
        "current turn should be null to force to quit from chain", game.getCurrentMove());
    Assert.assertEquals(
        "current turn value should be set to previous turn variable",
        currentTurn,
        game.getPreviousMove());
  }

  /** Test when game is over. */
  @Test
  public void testWhenGameIsOver() {
    game.setOver(Boolean.TRUE);

    game.getBoard().getHouses()[3] = 3;
    sowStones.implement(game);
    Move currentTurn = game.getCurrentMove();
    lastSownInPlayerStore.implement(game);

    Assert.assertNotNull("if game is over, current turn should NOT be null", game.getCurrentMove());
    Assert.assertNotEquals(
        "If game is over, previous turn should NOT be set with current turn",
        game.getPreviousMove(),
        currentTurn);
  }
}
