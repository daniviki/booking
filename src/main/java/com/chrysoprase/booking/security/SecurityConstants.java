package com.chrysoprase.booking.security;

public class SecurityConstants {
  public static final String SECRET = "pizza";
  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String USER_SIGN_UP_URL = "/user/sign-up";
  public static final String COMPANY_SIGN_UP_URL = "/company/sign-up";
  public static final String MAIN_URL = "/home";
  public static final String LOGIN_URL = "/login";
}
