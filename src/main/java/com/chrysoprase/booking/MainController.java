package com.chrysoprase.booking;

import com.chrysoprase.booking.appuser.AppUser;
import com.chrysoprase.booking.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  private BCryptPasswordEncoder passwordEncoder;
  private AppUserService userService;

  @Autowired
  public MainController(AppUserService userService, BCryptPasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/sign-up")
  public void signUp(@RequestBody AppUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);
  }
}
