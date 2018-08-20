package com.backbase.assignment.kalah.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Simple utility module to make it easy to plug in the server identifier when integrating the
 * server.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Component
public class ServerInfo {

  /** The constant GAMES_URI. */
  public static final String GAMES_URI = "http://%s:%s/games/%s";

  @Autowired private Environment environment;

  /**
   * Return the game identification associated with server.
   *
   * @param id the id
   * @return the game uri
   */
  public String getGameUri(Long id) {
    try {
      return String.format(
          GAMES_URI,
          InetAddress.getLocalHost().getHostAddress(),
          environment.getProperty("server.port"),
          id);
    } catch (UnknownHostException e) {
      return String.format(
          GAMES_URI,
          InetAddress.getLoopbackAddress().getHostAddress(),
          environment.getProperty("server.port"),
          id);
    }
  }
}
