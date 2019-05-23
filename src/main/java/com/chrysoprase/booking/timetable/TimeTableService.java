package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.exceptions.ReservedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TimeTableService {

  private TimeTableRepo timeTableRepo;

  @Autowired
  public TimeTableService(TimeTableRepo timeTableRepo) {
    this.timeTableRepo = timeTableRepo;
  }

  public List<Date> findTimeTableByEmployee(String name) {
    return timeTableRepo.findByEmployee(name);
  }

  public boolean ifReservedDate(String name, Date date) throws ReservedDate {
    List<Date> reservedDates = findTimeTableByEmployee(name);
    for (Date bookingDate : reservedDates) {
      if (bookingDate == date) {
        throw new ReservedDate(name + " is not avilable at this time-slot.");
      }
    }
    return true;
  }
}
