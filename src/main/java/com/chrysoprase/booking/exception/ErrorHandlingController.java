package com.chrysoprase.booking.exception;

import com.chrysoprase.booking.exception.MissingParameterException;
import com.greenfoxacademy.tribeschrysoprase.exception.invaliduserexception.InvalidUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingController {

  private Logger logger = LoggerFactory.getLogger(ErrorHandlingController.class);

  @ResponseBody
  @ExceptionHandler(value = MissingParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMsg handleMissingParameter(MissingParameterException exception) {
    logger.error("Caused by: {}", exception.getMessage());
    return new ErrorMsg("error", exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = WrongPasswordException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorMsg handleWrongPassword(WrongPasswordException exception) {
    logger.error("Caused by: {}", exception.getMessage());
    return new ErrorMsg("error", exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorMsg handleUsernameNotFound(UsernameNotFoundException exception) {
    logger.error("Caused by: {}", exception.getMessage());
    return new ErrorMsg("error", exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = InvalidUserException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorMsg handleInvalidUser(InvalidUserException exception) {
    logger.error("Caused by: {}", exception.getMessage());
    return new ErrorMsg("error", exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = ReservedDate.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMsg handleMissingParameter(ReservedDate exception) {
    return new ErrorMsg("error", exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(EmployeeNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMsg handleMissingParameter(EmployeeNotFound exception) {
    return new ErrorMsg("error", exception.getMessage());
  }
}
