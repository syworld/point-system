package com.world.pointsystem.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
 * 비즈니스 로직의 모든 Custom RuntimeException은 GlobalHttpException을 상속받도록 설계한다.
 * GlobalHttpException은 GlobalExceptionHandler에 의해 예외 처리된다.
 */
@Getter
public abstract class GlobalHttpException extends RuntimeException {

  private final HttpStatus status;
  private final String message;
  private final LogLevel logLevel;

  public GlobalHttpException(HttpStatus status, String message, LogLevel logLevel) {
    super(message);
    this.status = status;
    this.message = message;
    this.logLevel = logLevel;
  }

}
