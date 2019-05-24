package com.chrysoprase.booking.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
  boolean existsByName(String name);

  boolean existsByEmail(String email);

  List<Company> findCompaniesByUtility_TypeIsContaining(String utility);

  Company findCompanyById(Long id);

}
