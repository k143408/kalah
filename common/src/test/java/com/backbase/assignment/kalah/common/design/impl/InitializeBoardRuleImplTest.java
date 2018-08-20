package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test of InitializeBoardRule
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class InitializeBoardRuleImplTest {

  private Game game;

  private final InitializeBoardRuleImpl newBoard = new InitializeBoardRuleImpl();

  /** Sets up. */
  @Before
  public void setUp() {
    game =
        new Game(
            Long.MIN_VALUE,
            Arrays.asList(SouthPlayer.builder().build(), NorthPlayer.builder().build()));
  }

  /** Test implementation of rule if game is over. */
  @Test
  public void testImplementationOfRuleIfGameIsOver() {
    game.setOver(Boolean.TRUE);
    newBoard.implement(game);
    Assert.assertEquals(
        "If game is over, elements in array should NOT be changed",
        0,
        Arrays.stream(game.getBoard().getHouses()).sum());
  }

  /** Test implementation of rule if game is not over. */
  @Test
  public void testImplementationOfRuleIfGameIsNotOver() {
    newBoard.implement(game);

    int[] firstPlayerRangeIncludingStore = Arrays.copyOfRange(game.getBoard().getHouses(), 0, 7);
    int[] secondPlayerRangeIncludingStore = Arrays.copyOfRange(game.getBoard().getHouses(), 7, 14);

    Assert.assertArrayEquals(
        "after initial filling, range for any player should be {6,6,6,6,6,6,0}",
        new int[] {6, 6, 6, 6, 6, 6, 0},
        firstPlayerRangeIncludingStore);
    Assert.assertArrayEquals(
        "after initial filling, range for any player should be {6,6,6,6,6,6,0}",
        new int[] {6, 6, 6, 6, 6, 6, 0},
        secondPlayerRangeIncludingStore);
    Assert.assertTrue("Board should be filled", game.getBoard().isFilled());
    Assert.assertTrue("Game should be started", game.isStarted());
  }
}
