package com.chrysoprase.booking.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

  Company findCompanyById(Long id);

}
