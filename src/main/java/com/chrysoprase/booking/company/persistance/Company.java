package com.chrysoprase.booking.company.persistance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;*/

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

  /*@OneToMany
  private List<Employee> employees;

  @ManyToMany
  private List<Utility> utilities;*/
}
