package com.chrysoprase.booking.exception;

public class WrongPasswordException extends Exception {
  public WrongPasswordException(String message) {
    super(message);
  }
}
