package com.world.pointsystem.event.exception;

import com.world.pointsystem.global.exception.GlobalHttpException;
import com.world.pointsystem.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class EventNotFoundException extends GlobalHttpException {

  public EventNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message, LogLevel.DEBUG);
  }

}
