package com.backbase.assignment.kalah.cache;

import com.backbase.assignment.kalah.common.domain.Game;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The Cache consists of entries for a {@link Game} . It is implemented based on a {@link
 * ConcurrentHashMap}. Cache is the set of sequential and parallel bulk operations
 *
 * @see ConcurrentHashMap
 * @author Jibran Tariq
 * @version 1.0
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Cache {

  private ConcurrentHashMap<Long, Game> inMemory;

  Cache() {
    this.inMemory = new ConcurrentHashMap<>();
  }

  /**
   * Gets game object from memory. If {@link Game} is not there then null will be passed as value.
   *
   * @param id the id
   * @return the game
   */
  public Game getGame(Long id) {
    return inMemory.getOrDefault(id, null);
  }

  /**
   * Add game object into memory
   *
   * @param id the id
   * @param game the game
   */
  public void addGame(Long id, Game game) {
    inMemory.putIfAbsent(id, game);
  }
}
