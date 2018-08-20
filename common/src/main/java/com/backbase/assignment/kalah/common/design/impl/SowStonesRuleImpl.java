package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.design.RuleImplementor;
import com.backbase.assignment.kalah.common.design.helper.CheckGetMoveRule;
import com.backbase.assignment.kalah.common.design.helper.SowingStoneRule;
import com.backbase.assignment.kalah.common.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Players take turns sowing their stones. On a move, the player removes all stones from one of the
 * houses under their control. Moving counter-clockwise, the player drops one stone in each house in
 * move, including the player's own store but not their opponent's.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Profile("!test")
@Service
@Slf4j
@Order(1)
public class SowStonesRuleImpl implements RuleImplementor, CheckGetMoveRule, SowingStoneRule {

  /**
   * By implementing the rule, it means that If the game is not over and player can move from chosen
   * house, sow the stones from that house anti-clockwise.
   *
   * @param game {@link Game}
   */
  @Override
  public void implement(Game game) {
    if (!game.isOver()) {
      int selectedHouse = game.getCurrentMove().getSelectedHouse();
      if (checkMoveOfPlayer(game, selectedHouse)) {
        performSowingTheStones(game, selectedHouse, opponentStoreIndex(game));
      }
    }
  }
}
