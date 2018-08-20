package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.domain.Board;
import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.Move;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The unit test of {@link SowStonesRuleImpl}
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public class SowStonesRuleImplTest {

  private Game game;
  private final InitializeBoardRuleImpl boardRule = new InitializeBoardRuleImpl();
  private final SowStonesRuleImpl sowStones = new SowStonesRuleImpl();

  /** Sets up. */
  @Before
  public void setUp() {
    game =
        new Game(
            Long.MIN_VALUE,
            Arrays.asList(SouthPlayer.builder().build(), NorthPlayer.builder().build()));
    boardRule.implement(game);
  }

  /** Test implementation sow stones rule. */
  @Test
  public void testImplementationSowStonesRule() {
    game.setCurrentMove(new Move(3));
    game.setTakeTurnPlayer(new SouthPlayer());

    sowStones.implement(game);

    Assert.assertArrayEquals(
        "After sowing, number of stones in the houses should be changed",
        new int[] {6, 6, 6, 0, 7, 7, 1, 7, 7, 7, 6, 6, 6, 0},
        game.getBoard().getHouses());

    Assert.assertEquals(
        "After sowing, last sown index for current turn should be kept",
        9,
        game.getCurrentMove().getLastSownIndex());
  }

  /** Test implementation rule for correct user with 10 stones in the house. */
  @Test
  public void testImplementationRuleForCorrectUserWith10StonesInTheHouse() {
    game.setCurrentMove(new Move(3));
    game.setTakeTurnPlayer(new SouthPlayer());
    // changing value to 20 to check corner-case with opponent store. it should not be filled out in
    // our attempt
    game.getBoard().getHouses()[3] = 20;

    sowStones.implement(game);

    Assert.assertArrayEquals(
        "After sowing, number of stones in the houses should be changed",
        new int[] {7, 7, 7, 1, 8, 8, 2, 8, 8, 8, 8, 7, 7, 0},
        game.getBoard().getHouses());

    Assert.assertEquals(
        "After sowing, last sown index for current turn should be kept",
        10,
        game.getCurrentMove().getLastSownIndex());
  }

  /** Test implementation rule for incorrect player. */
  @Test
  public void testImplementationRuleForIncorrectPlayer() {
    game.setCurrentMove(new Move(3));
    game.setTakeTurnPlayer(new NorthPlayer());
    Board boardBeforeSowing = game.getBoard();
    sowStones.implement(game);
    Assert.assertArrayEquals(
        "Sowing should NOT happen since player cannot take a turn",
        boardBeforeSowing.getHouses(),
        game.getBoard().getHouses());
  }
}
