package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.EmployeeService;
import com.chrysoprase.booking.exception.ReservedDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TimeTableServiceTest {

  private TimeTableService timeTableService;

  @Mock
  private TimeTableRepo timeTableRepo;

  @Mock
  private EmployeeService employeeService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    timeTableService = new TimeTableService(timeTableRepo, employeeService);
  }

  @Test
  public void timeTableService_isReservedDate_returnsTrue() throws ReservedDate {
    Date date = new Date();
    List<Timestamp> emptyList = new ArrayList<>();
    when(timeTableService.findTimeTableByEmployee(any(String.class))).thenReturn(emptyList);
    assertTrue(timeTableService.ifReservedDate("Nincs", date));
  }
}
