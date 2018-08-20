package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.design.RuleImplementor;
import com.backbase.assignment.kalah.common.design.helper.InitGameRule;
import com.backbase.assignment.kalah.common.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * At the starting of the game, Fill houses in board with six stones that accept stores owned by
 * each player.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Profile("!test")
@Service
@Slf4j
@Order(0)
public class InitializeBoardRuleImpl implements RuleImplementor, InitGameRule {

  /**
   * By implementing the rule, it means that if the game is not over, fill the board and prepare it
   * system to start the game.
   *
   * @param game {@link Game}
   */
  @Override
  public void implement(Game game) {
    if (!game.isOver()) {
      fillBoardAtStartingOfGame(game, findIndexesOfStore(game));
    }
  }
}
