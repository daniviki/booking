package com.chrysoprase.booking.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

  private AppUserRepository appUserRepository;

  @Autowired
  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  public AppUser findAppUserById(Integer id) {
    return appUserRepository.findAppUserById(id);
  }

  public AppUser findUserByUsername(String userName) {
    return appUserRepository.findByUsername(userName);
  }

  public void saveUser(AppUser user) {
    appUserRepository.save(user);
  }

  public boolean isDatabaseContainsUsername(String username) {
    return appUserRepository.existsAppUserByUsername(username);
  }

  public String missingParameter(AppUser user) {
    String parameters = "";
    if (user.getUsername() == null && user.getPassword() == null) {
      return parameters = "username, password";
    }
    if (user.getUsername() == null) {
      return parameters = "username";
    }
    if (user.getPassword() == null) {
      return parameters = "password";
    }
    return parameters;
  }
}
