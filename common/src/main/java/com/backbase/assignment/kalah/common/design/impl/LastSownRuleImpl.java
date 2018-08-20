package com.backbase.assignment.kalah.common.design.impl;

import com.backbase.assignment.kalah.common.design.RuleImplementor;
import com.backbase.assignment.kalah.common.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * If the final stone is in store players, the player gets a further step. There is no limit to the
 * number of steps that a player can play in their move.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Profile("!test")
@Service
@Slf4j
@Order(2)
public class LastSownRuleImpl implements RuleImplementor {

  /**
   * By implementing the rule, it means leave the move of player as-is and set current move to null
   * to skip next steps if last stone lands in the store.
   *
   * @param game {@link Game}
   */
  @Override
  public void implement(Game game) {
    if (!game.isOver()) {
      int lastSownIndex = game.getCurrentMove().getLastSownIndex();
      if (game.getTakeTurnPlayer().storeIndex() == lastSownIndex) {
        game.setPreviousMove(game.getCurrentMove());
        game.setCurrentMove(null);
      }
    }
  }
}
