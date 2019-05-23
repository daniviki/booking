package com.chrysoprase.booking.employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

  Employee findEmployeeById(Long id);

}
