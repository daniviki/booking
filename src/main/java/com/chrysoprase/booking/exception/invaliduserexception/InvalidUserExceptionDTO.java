package com.greenfoxacademy.tribeschrysoprase.exception.invaliduserexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidUserExceptionDTO {
  private String status;
  private String error;
}
