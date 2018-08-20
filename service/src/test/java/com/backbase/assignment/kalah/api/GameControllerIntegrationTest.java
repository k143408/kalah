package com.backbase.assignment.kalah.api;

import com.backbase.assignment.kalah.KalahApplication;
import com.backbase.assignment.kalah.common.dto.GameResponse;
import com.backbase.assignment.kalah.common.dto.GameResponseWithStatus;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/** The type Game controller it. */
@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = KalahApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntegrationTest {

  @LocalServerPort private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();

  private HttpHeaders headers = new HttpHeaders();

  private HttpEntity<String> entity = null;

  private Long gameId = new Long(0);

  /** Sets up the headers */
  @Before
  public void setUp() {
    headers.setContentType(MediaType.APPLICATION_JSON);
    entity = new HttpEntity<>(null, headers);
  }

  /** Test when game is created from end point. */
  @Test
  public void testWhenGameIsCreatedFromEndPoint() {

    ResponseEntity<GameResponse> response =
        restTemplate.exchange(
            createURLWithPort("/games"), HttpMethod.POST, entity, GameResponse.class);

    Assert.assertEquals(
        "Game should be created with CREATE Status code",
        response.getStatusCode(),
        HttpStatus.CREATED);

    Assert.assertEquals(
        "New game id should be generated and incremented", response.getBody().getId(), ++gameId);

    Assert.assertTrue(
        "Game uri should contains generated id",
        response.getBody().getUri().contains(gameId.toString()));

    testWhenFoundMoveOfGameOccursFromEndPoint();
  }

  /** Test when found move of game occurs from end point. */
  private void testWhenFoundMoveOfGameOccursFromEndPoint() {

    ResponseEntity<GameResponseWithStatus> response =
        restTemplate.exchange(
            createURLWithPort("/games/1/pits/" + gameId.toString()),
            HttpMethod.PUT,
            entity,
            GameResponseWithStatus.class);

    Assert.assertEquals("Current game id should be 1", response.getBody().getId(), gameId);

    Assert.assertEquals(response.getBody().getStatus().get("1"), "0");

    Assert.assertEquals(response.getBody().getStatus(), expectedStatusOfFirstMoveOfGame());
  }

  private Map<String, String> expectedStatusOfFirstMoveOfGame() {
    Map<String, String> map = new HashMap<>();
    map.put("1", "0");
    map.put("2", "7");
    map.put("3", "7");
    map.put("4", "7");
    map.put("5", "7");
    map.put("6", "7");
    map.put("7", "1");
    map.put("8", "6");
    map.put("9", "6");
    map.put("10", "6");
    map.put("11", "6");
    map.put("12", "6");
    map.put("13", "6");
    map.put("14", "0");
    return map;
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
