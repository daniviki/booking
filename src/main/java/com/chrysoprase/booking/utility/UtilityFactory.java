package com.chrysoprase.booking.utility;

public class UtilityFactory {
  public static Utility makeUtility(UtilityType type) {
    return type.generateUtility();
  }

}
