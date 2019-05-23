package com.chrysoprase.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

  @PostMapping("/sign-up")
  public void signUp(@RequestBody ApplicationUser user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    applicationUserRepository.save(user);
  }
}
