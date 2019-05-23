package com.chrysoprase.booking;

import com.chrysoprase.booking.exception.MissingParameterException;
import com.chrysoprase.booking.appuser.AppUser;
import com.chrysoprase.booking.appuser.AppUserService;
import com.chrysoprase.booking.exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainService {

  private AppUserService userService;

  @Autowired
  public MainService(AppUserService userService) {
    this.userService = userService;
  }

  public ResponseEntity login(AppUser user) throws WrongPasswordException,
          MissingParameterException {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    boolean isDatabaseContainsUsername =
            userService.isDatabaseContainsUsername(user.getUsername());
    String parameters = userService.missingParameter(user);
    if (!parameters.equals("")) {
      throw new MissingParameterException("Missing parameter(s): " + parameters);
    } else if (parameters.equals("") && !isDatabaseContainsUsername) {
      throw new UsernameNotFoundException("No such user: " + user.getUsername());
    } else if (parameters.equals("")
            && !passwordEncoder.matches(user.getPassword(),
            userService.findUserByUsername(user.getUsername()).getPassword())) {
      throw new WrongPasswordException("Wrong password!");
    }
    AppUser userToReturn = userService.findUserByUsername(user.getUsername());
    return ResponseEntity.ok(userToReturn);
  }

  private AppUser getUserFromAuth(Authentication authentication) {
    String username = authentication.getPrincipal().toString();
    return userService.findUserByUsername(username);
  }

}
