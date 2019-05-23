package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class TimeTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Timestamp reservedDate;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Employee employee;

  public TimeTable(Employee employee, Timestamp date) {
    this.employee = employee;
    this.reservedDate = date;
  }
}
