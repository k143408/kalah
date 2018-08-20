package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.design.RuleImplementor;
import com.backbase.assignment.kalah.common.design.helper.CaptureIntoPlayerStoreRule;
import com.backbase.assignment.kalah.common.design.helper.CheckGetMoveRule;
import com.backbase.assignment.kalah.common.design.helper.WrapTheMoveRule;
import com.backbase.assignment.kalah.common.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * If the last stone falls in an empty house owned by the player, and the corresponding house
 * contains seeds, Both the last seeds and the opposite seeds are captured and placed in the
 * player's store.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Profile("!test")
@Service
@Slf4j
@Order(3)
public class LastSownInEmptyHouseRuleImpl
    implements RuleImplementor, CheckGetMoveRule, WrapTheMoveRule, CaptureIntoPlayerStoreRule {

  /**
   * By implementing the rule, it means that each element changes in game based on rules, depending
   * on the responsibility.
   *
   * @param game {@link Game}
   */
  @Override
  public void implement(Game game) {
    if (game.getCurrentMove() != null && !game.isOver()) {
      int lastSownIndex = game.getCurrentMove().getLastSownIndex();

      if (!checkMoveOfPlayer(game, lastSownIndex)) {
        wrapCurrentMove(game);
        return;
      }

      captureOppositeAndCurrentHouseIntoPlayerStore(game, lastSownIndex);
      wrapCurrentMove(game);
    }
  }
}
