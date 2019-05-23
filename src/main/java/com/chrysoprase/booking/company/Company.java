package com.chrysoprase.booking.company;

import com.chrysoprase.booking.employee.Employee;
import com.chrysoprase.booking.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String password;
  private String email;

  @OneToMany
  private List<Employee> employees;

  @ManyToMany
  private List<Utility> utilities;
}
