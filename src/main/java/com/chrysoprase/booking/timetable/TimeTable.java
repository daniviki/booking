package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class TimeTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private List<Date> startAt;

  @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
  public Employee employee;

}
