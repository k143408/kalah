package com.backbase.assignment.kalah;

import com.backbase.assignment.kalah.common.domain.Game;
import com.backbase.assignment.kalah.common.domain.player.NorthPlayer;
import com.backbase.assignment.kalah.common.domain.player.SouthPlayer;
import java.util.Arrays;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/** The type Kalah application tests. */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KalahApplication.class)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class KalahApplicationTests {

  /** The Mock game object. */
  protected final Game mockGame =
      new Game(
          Long.valueOf(1),
          Arrays.asList(NorthPlayer.builder().build(), SouthPlayer.builder().build()));

  /** The Mock details of sever info. */
  protected final String mockSeverInfo = "http://server:port/games/1";
}
