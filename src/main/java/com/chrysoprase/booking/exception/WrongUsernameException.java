package com.chrysoprase.booking.exception;

public class WrongUsernameException extends Exception {
  public WrongUsernameException(String message) {
    super(message);
  }
}