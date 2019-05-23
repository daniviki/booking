package com.chrysoprase.booking.appuser;

import org.springframework.beans.factory.annotation.Autowired;
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
}
