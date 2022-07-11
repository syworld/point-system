package com.world.pointsystem.event.enums;

public enum Point {
  MINUS(-1),
  ZERO(0),
  PLUS(1);

  private final Integer value;

  Point(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
  
}
