package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.exceptions.EmployeeNotFound;
import com.chrysoprase.booking.exceptions.ReservedDate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class TimeTableController {

  private TimeTableService timeTableService;

  public TimeTableController(TimeTableService timeTableService) {
    this.timeTableService = timeTableService;
  }

  @PostMapping("/book")
  public void bookAppointment(@RequestBody TimeTableDAO timeTableDAO) throws ReservedDate,
          ParseException, EmployeeNotFound {
    timeTableService.bookAnAppointment(timeTableDAO);
  }
}
