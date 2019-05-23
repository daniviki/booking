package com.chrysoprase.booking.appuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

  AppUser findAppUserById(Integer id);
  AppUser findByUsername(String userName);

}
