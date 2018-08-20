package com.backbase.assignment.kalah.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The {@code Move} class contains state of the index of selected house and the last sown index
 *
 * @author Jibran Tariq
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Move {

  /** The selected house. */
  @NonNull private int selectedHouse;

  /** The Last sown index. */
  @Setter private int lastSownIndex;
}
