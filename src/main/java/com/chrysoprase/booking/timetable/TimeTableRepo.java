package com.chrysoprase.booking.timetable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {

  List<Date> findByEmployee(String name);
}
