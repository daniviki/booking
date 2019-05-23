package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.Employee;
import com.chrysoprase.booking.employee.EmployeeService;
import com.chrysoprase.booking.exception.EmployeeNotFound;
import com.chrysoprase.booking.exception.ReservedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
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
  private static final String MAILGUN_API_KEY
          = "158d5ba190bcf4bd90afacf43ca22572-52b0ea77-23ee444c";
  private static final String MAILGUN_DOMAIN_NAME
          = "sandboxc16c1932d9384d5c974eb932752f791d.mailgun.org";

  @Autowired
  public TimeTableService(TimeTableRepo timeTableRepo, EmployeeService employeeService) {
    this.timeTableRepo = timeTableRepo;
    this.employeeService = employeeService;
  }

  public List<Timestamp> findTimeTableByEmployee(String name) {
    Employee employee = employeeService.findByEmployeeName(name);
    return timeTableRepo.findByEmployee(employee).stream()
            .map(TimeTable::getDate)
            .collect(Collectors.toList());
  }

  private boolean ifReservedDate(String name, Date date) throws ReservedDate {
    List<Timestamp> reservedDates = findTimeTableByEmployee(name);
    if (reservedDates == null) {
      return true;
    }
    Timestamp dbdate = new Timestamp(date.getTime());
    for (Timestamp bookingDate : reservedDates) {
      if (bookingDate.equals(dbdate)) {
        throw new ReservedDate(name + " is not avilable at this time-slot.");
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

  private static ClientResponse sendSimpleMessage(String recipient) {
    Client client = Client.create();
    client.addFilter(new HTTPBasicAuthFilter("api", MAILGUN_API_KEY));
    MultivaluedMapImpl formData = new MultivaluedMapImpl();
    formData.add("from", "Hackathon <chrysoprase>");
    formData.add("to", recipient);
    formData.add("subject", "Simple Mailgun Example");
    formData.add("text", "Plaintext content");
    WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME
            + "/messages");
    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
            formData);
  }
}
