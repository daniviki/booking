package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {

  List<TimeTable> findByEmployee(Employee employee);
}
