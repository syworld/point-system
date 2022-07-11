package com.world.pointsystem.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GlobalExceptionResponse {


  private final HttpStatus status;
  private final String errorCode;
  private final String message;
  private final String path;

}
