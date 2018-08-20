package com.backbase.assignment.kalah.actuator;

import com.backbase.assignment.kalah.common.design.RuleImplementor;
import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.Move;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.Player;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import com.backbase.assignment.kalah.common.exception.KalahException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a service that applies all the rules or controls game info.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Service
@Slf4j
public class GameActuator {

  /** The Rule implementors. */
  @Autowired(required = false)
  private List<RuleImplementor> ruleImplementors;

  /** The South player. */
  private final Player southPlayer = SouthPlayer.builder().build();

  /** The North player. */
  private final Player northPlayer = NorthPlayer.builder().build();

  /**
   * Create game with basic setup.
   *
   * @param id the id
   * @return the game
   */
  public Game create(Long id) {
    Game game =
        new Game(id, Arrays.asList(SouthPlayer.builder().build(), NorthPlayer.builder().build()));
    game.setTakeTurnPlayer(game.getPlayers().get(0));
    return game;
  }

  /**
   * Perform move on the game on selected house with all rules.
   *
   * @param game the game
   * @param selectedHouse the selected house
   * @return the updated game
   */
  public Game performMove(Game game, Integer selectedHouse) {
    log.info("Getting game {} and selected house {}", game, selectedHouse);
    Game updateGame = setGameWithMove(game, selectedHouse);
    log.info("Update game {} and selected house {}", game, selectedHouse);
    if (ruleImplementors != null) ruleImplementors.forEach(rule -> rule.implement(updateGame));
    return updateGame;
  }

  /**
   * Checking the selected House is validate or not.
   *
   * @param game - Game object
   * @param selectedHouse - selected House.
   */
  private Game setGameWithMove(Game game, Integer selectedHouse) {
    if (selectedHouse < 0 || selectedHouse > 13)
      throw new KalahException("Invalid selected pit for Players");
    Player nextPlayer = game.getTakeTurnPlayer() == null ? southPlayer : game.getTakeTurnPlayer();

    if (nextPlayer.name().equalsIgnoreCase(southPlayer.name())) {
      if (selectedHouse >= southPlayer.storeIndex()) {
        throw new KalahException("Invalid selected pit for South Player");
      }
    } else if (nextPlayer.name().equalsIgnoreCase(northPlayer.name())) {
      if (selectedHouse >= northPlayer.storeIndex() || selectedHouse <= southPlayer.storeIndex()) {
        throw new KalahException("Invalid selected pit for North Player");
      }
    }
    game.setCurrentMove(new Move(selectedHouse));
    return game;
  }

  /**
   * Convert array of house of game to informational map.
   *
   * @param game the game
   * @return the game boardto map
   */
  public Map mapGameBoardToMapObject(Game game) {
    Map<String, String> map = new HashMap<>();

    int pos = 1;
    for (int house : game.getBoard().getHouses()) {
      map.put("" + pos++, house + "");
    }
    return map;
  }
}
