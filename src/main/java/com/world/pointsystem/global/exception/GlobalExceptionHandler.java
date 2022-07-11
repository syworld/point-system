package com.world.pointsystem.global.exception;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 전역 예외 처리 핸들러
 모든 RuntimeException을 공통 response format인 GlobalExceptionResponse로 처리한다.
 프로젝트에서 정의하지 않은 Exception이 발생하면 INTERNAL SERVER ERROR로 처리한다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


  private static final String ERROR_MESSAGE_FORMAT = "[EXCEPTION] path: {} | errorCode: {} | message: {}";

  private String getErrorCode(Exception e) {
    return e.getClass().getSimpleName().replace("Exception", "");
  }

  private void createLog(LogLevel logLevel, Exception e, String requestPath) {
    String errorCode = getErrorCode(e);
    if (logLevel == LogLevel.NONE) {
      return;
    }
    if (logLevel == LogLevel.ERROR) {
      log.error(ERROR_MESSAGE_FORMAT, requestPath,
          errorCode,
          e.getMessage(), e);
    } else {
      log.warn(ERROR_MESSAGE_FORMAT, requestPath,
          errorCode,
          e.getMessage(), e);
    }
  }

  @ExceptionHandler(GlobalHttpException.class)
  public ResponseEntity<GlobalExceptionResponse> handleGlobalHttpException(GlobalHttpException e,
      HttpServletRequest request) {
    GlobalExceptionResponse response = new GlobalExceptionResponse(e.getStatus(),
        getErrorCode(e), e.getMessage(), request.getRequestURI());
    createLog(e.getLogLevel(), e, request.getRequestURI());
    return ResponseEntity.status(e.getStatus()).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GlobalExceptionResponse> handleException(Exception e,
      HttpServletRequest request) {
    GlobalExceptionResponse response = new GlobalExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        getErrorCode(e), null, request.getRequestURI());
    createLog(LogLevel.ERROR, e, request.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GlobalExceptionResponse> handleMethodArgumentNotValidExceptionException(
      MethodArgumentNotValidException e,
      HttpServletRequest request) {
    String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    GlobalExceptionResponse response = new GlobalExceptionResponse(HttpStatus.BAD_REQUEST,
        getErrorCode(e), errorMessage,
        request.getRequestURI());
    createLog(LogLevel.ERROR, e, request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
