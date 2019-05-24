package com.chrysoprase.booking.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

  Employee findEmployeeByName(String name);

  Boolean existsEmployeeByName(String name);

}
