package com.world.pointsystem.event.exception;

import com.world.pointsystem.global.exception.GlobalHttpException;
import com.world.pointsystem.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class InvalidReviewActionException extends GlobalHttpException {

  public InvalidReviewActionException(String message) {
    super(HttpStatus.BAD_REQUEST, message, LogLevel.ERROR);
  }

}
