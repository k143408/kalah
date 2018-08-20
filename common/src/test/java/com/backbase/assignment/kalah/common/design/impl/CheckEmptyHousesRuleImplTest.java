package com.backbase.assignment.kalah.common.design.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test of CheckEmptyHousesRule
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class CheckEmptyHousesRuleImplTest {

  private Game game;

  private CheckEmptyHousesRuleImpl checkEmptzyHousesRule = new CheckEmptyHousesRuleImpl();
  private InitializeBoardRuleImpl board = new InitializeBoardRuleImpl();

  /** Sets up. */
  @Before
  public void setUp() {
    game =
        new Game(
            Long.MIN_VALUE,
            Arrays.asList(SouthPlayer.builder().build(), NorthPlayer.builder().build()));
    board.implement(game);
  }

  /** Test of not null instance. */
  @Test
  public void testOfNotNullInstance() {
    Assert.assertNotNull("Board should not be null", board);
    Assert.assertNotNull("CheckEmptyHousesRuleImpl should not be null", checkEmptzyHousesRule);
    Assert.assertNotNull("Game instance should not be null", game);
  }

  /** Test implementation of rule without empty houses. */
  @Test
  public void testImplementationOfRuleWithoutEmptyHouses() {
    checkEmptzyHousesRule.implement(game);

    assertFalse("Game should NOT be over", game.isOver());
    assertNull("Winner should be NOT defined since game is not over", game.getWinner());
  }

  /** Test implementation of rule with empty houses and south winner. */
  @Test
  public void testImplementationOfRuleWithEmptyHousesAndSouthWinner() {

    int[] houses = game.getBoard().getHouses();
    for (int i = 0; i < Board.HOUSES_PER_PLAYER; i++) {
      houses[i] = 0;
    }
    houses[6] = 40;
    checkEmptzyHousesRule.implement(game);

    assertTrue("Game should be over", game.isOver());
    assertEquals(
        "Winner should be defined since game is over",
        SouthPlayer.builder().build().name(),
        game.getWinner());
    Assert.assertArrayEquals(
        "After game is finished, all the houses should be empty",
        new int[] {0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 36},
        game.getBoard().getHouses());
  }

  /** Test implementation of rule with empty houses and north winner. */
  @Test
  public void testImplementationOfRuleWithEmptyHousesAndNorthWinner() {

    int[] houses = game.getBoard().getHouses();
    for (int i = 7; i < 14; i++) {
      houses[i] = 0;
    }
    houses[13] = 40;
    checkEmptzyHousesRule.implement(game);

    assertTrue("Game should be over", game.isOver());
    assertEquals(
        "Winner should be defined since game is over",
        NorthPlayer.builder().build().name(),
        game.getWinner());
    Assert.assertArrayEquals(
        "After game is finished, all the houses should be empty",
        new int[] {0, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 40},
        game.getBoard().getHouses());
  }

  /** Test implementation of rule with empty houses and draw. */
  @Test
  public void testImplementationOfRuleWithEmptyHousesAndDraw() {
    // set up 0 for south player
    int[] houses = game.getBoard().getHouses();
    for (int i = 0; i < Board.HOUSES_PER_PLAYER; i++) {
      houses[i] = 0;
    }
    houses[6] = 36;

    checkEmptzyHousesRule.implement(game);

    assertTrue("Game should be over", game.isOver());
    assertEquals("DRAW should be set since game is over", "DRAW", game.getWinner());
    Assert.assertArrayEquals(
        "After game is finished, all the houses should be empty",
        new int[] {0, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 36},
        game.getBoard().getHouses());
  }
}
