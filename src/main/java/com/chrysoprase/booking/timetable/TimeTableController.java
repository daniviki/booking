package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.exception.EmployeeNotFound;
import com.chrysoprase.booking.exception.ErrorMsg;
import com.chrysoprase.booking.exception.ReservedDate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

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

  @PostMapping("/list/{name}")
  public List<Timestamp> listReservedAppointments(@PathVariable String name) {
    return timeTableService.findTimeTableByEmployee(name);
  }
}
