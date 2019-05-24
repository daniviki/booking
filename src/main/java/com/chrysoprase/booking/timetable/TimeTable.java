package com.chrysoprase.booking.timetable;

import com.chrysoprase.booking.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TimeTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Timestamp date;
  @ManyToOne
  @JoinColumn(name = "emloyee_id")
  @JsonIgnore
  private Employee employee;

  public TimeTable(Employee employee, Timestamp date) {
    this.employee = employee;
    this.date = date;
  }
}
