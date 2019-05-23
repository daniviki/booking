package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.Employee;
import com.chrysoprase.booking.employee.EmployeeService;
import com.chrysoprase.booking.exceptions.EmployeeNotFound;
import com.chrysoprase.booking.exceptions.ReservedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTableService {

  private TimeTableRepo timeTableRepo;
  private EmployeeService employeeService;

  @Autowired
  public TimeTableService(TimeTableRepo timeTableRepo, EmployeeService employeeService) {
    this.timeTableRepo = timeTableRepo;
    this.employeeService = employeeService;
  }

  public List<Date> findTimeTableByEmployee(String employeeName) {
    Employee employee = employeeService.findByEmployeeName(employeeName);
    return timeTableRepo.findByEmployee(employee).stream()
            .map(TimeTable::getReservedDate)
            .collect(Collectors.toList());
  }

  private boolean ifReservedDate(String employeeName, Date date) throws ReservedDate {
    List<Date> reservedDates = findTimeTableByEmployee(employeeName);
    if (reservedDates == null) {
      return true;
    }
    for (Date bookingDate : reservedDates) {
      if (bookingDate == date) {
        throw new ReservedDate(employeeName + " is not avilable at this time-slot.");
      }
    }
    return true;
  }

  private void addNewBooking(String name, Timestamp date) throws EmployeeNotFound {
    if (!employeeService.isEmployeeExists(name)) {
      throw new EmployeeNotFound("No such name in our database.");
    }
    Employee emp = employeeService.findByEmployeeName(name);
    TimeTable timeTable = new TimeTable(emp, date);
    timeTableRepo.save(timeTable);
  }

  public synchronized void bookAnAppointment(TimeTableDAO timeTableDAO) throws
          ReservedDate, ParseException, EmployeeNotFound {
    String date = timeTableDAO.getDate();
    String name = timeTableDAO.getName();
    String pattern = "MM-dd-yyyy HH:mm:SS";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    Date newDate = simpleDateFormat.parse(date);
    if (!ifReservedDate(name, newDate)) {
      Timestamp dbDate = new Timestamp(newDate.getTime());
      addNewBooking(name, dbDate);
    }
  }
}
