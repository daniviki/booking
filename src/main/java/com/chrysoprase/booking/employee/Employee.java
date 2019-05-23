package com.chrysoprase.booking.employee;

import com.chrysoprase.booking.timetable.TimeTable;
import com.chrysoprase.booking.company.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @ManyToOne
  private Company company;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST,
          orphanRemoval = true)
  private List<TimeTable> timeTables;
}
