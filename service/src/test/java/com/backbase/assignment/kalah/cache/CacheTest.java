package com.backbase.assignment.kalah.cache;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.backbase.assignment.kalah.KalahApplicationTests;
import com.backbase.assignment.kalah.common.domain.Game;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/** The type Cache test. */
public class CacheTest extends KalahApplicationTests {

  @Autowired private Cache cache;

  @MockBean private ConcurrentHashMap<Long, Game> inMemory;

  /** Test add instance of game. */
  @Test
  public void testAddInstanceOfGame() {

    given(inMemory.getOrDefault(anyLong(), any(Game.class))).willReturn(null);

    cache.addGame(Long.valueOf(1), mockGame);

    Assert.assertNull("Game object should be null", inMemory.get(Long.valueOf(1)));
  }

  /** Test get game instance. */
  @Test
  public void testGetGameInstance() {

    given(inMemory.getOrDefault(anyLong(), any())).willReturn(null);

    Game game = cache.getGame(Long.valueOf(1));

    Assert.assertNull("Game instance should be null ", game);
  }
}
