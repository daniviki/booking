package com.chrysoprase.booking.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public boolean isEmployeeExists(String name) {
    return employeeRepository.existsEmployeeByName(name);
  }

  public Employee findByEmployeeName(String name) {
    return employeeRepository.findEmployeeByName(name);
  }

}
