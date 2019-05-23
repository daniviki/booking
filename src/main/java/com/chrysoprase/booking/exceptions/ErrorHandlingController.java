package com.chrysoprase.booking.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingController {

  @ResponseBody
  @ExceptionHandler(value = ReservedDate.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMsg handleMissingParameter(ReservedDate exception) {
    return new ErrorMsg("error", exception.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = EmployeeNotFound.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMsg handleMissingParameter(EmployeeNotFound exception) {
    return new ErrorMsg("error", exception.getMessage());
  }
}
