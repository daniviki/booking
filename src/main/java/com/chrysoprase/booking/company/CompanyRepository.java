package com.chrysoprase.booking.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
  boolean existsByName(String name);

  boolean existsByEmail(String email);
}
