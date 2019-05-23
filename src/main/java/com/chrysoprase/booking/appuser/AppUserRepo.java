package com.chrysoprase.booking.appuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser,Integer> {
}
