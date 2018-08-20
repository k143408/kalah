package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.design.RuleImplementor;
import com.backbase.assignment.kalah.common.design.helper.MoveCheckingProcessRule;
import com.backbase.assignment.kalah.common.design.helper.WinnerAnalyzerRule;
import com.backbase.assignment.kalah.common.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * When a player no longer has seeds in one of their houses, the game ends. The other player
 * transfers all remaining seeds to his store, and the player with the most seeds in his store wins.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Profile("!test")
@Service
@Slf4j
@Order(4)
public class CheckEmptyHousesRuleImpl
    implements RuleImplementor, MoveCheckingProcessRule, WinnerAnalyzerRule {

  /**
   * By implementing the last rule, This means that if the game does not finish, check whether it is
   * one of them Player has all empty houses. If so, move the other player's stones to his store,
   * count the stones At each shop find the winner. Otherwise, continue the game.
   *
   * @param game {@link Game}
   */
  @Override
  public void implement(Game game) {
    if (!game.isOver()) {
      boolean isGameOver = analyseMove(game);
      if (isGameOver) {
        seekWinner(game);
      }
    }
  }
}
