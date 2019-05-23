package com.chrysoprase.booking.appuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

  AppUser findAppUserById(Long id);

  AppUser findByUsername(String userName);

  boolean existsAppUserByUsername(String userName);

}
