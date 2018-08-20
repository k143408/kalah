package com.backbase.assignment.kalah.common.design;

import com.backbase.assignment.kalah.common.domain.Game;

/**
 * The main implementor of all rules.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
public interface RuleImplementor {

  /**
   * By implementing the rule, it means that each element changes in game based on rules, depending
   * on the responsibility.
   *
   * @param game {@link Game}
   */
  void implement(Game game);
}
