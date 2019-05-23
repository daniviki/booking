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

  private AppUser findAppUserById(Long id) {
    return appUserRepository.findAppUserById(id);
  }
}
