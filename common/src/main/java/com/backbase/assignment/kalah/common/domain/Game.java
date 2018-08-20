package com.backbase.assignment.kalah.common.domain;

import com.backbase.assignment.kalah.common.domain.player.Player;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The {@code Game} class contains the {@link Board} with all the houses and stores with two {@link
 * Player}*s. There are two flags in this game. In starting of the game, the started flag which will
 * be marked as true at the beginning of the game and keep as-is till the end of the game and other
 * flage will be marked as true when the game is finished. The current {@link Move} will be shown
 * last sown index and chosen house to be the current move of Player.
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
@ToString
public class Game {

  private final Board board = Board.builder().build();

  @NonNull private Long id;

  @NonNull private final List<Player> players;

  @Setter private boolean over = Boolean.FALSE;

  @Setter private boolean started = Boolean.FALSE;

  @Setter private String winner;

  @Setter private Move currentMove;

  @Setter private Move previousMove;

  @Setter private Player takeTurnPlayer;
}
