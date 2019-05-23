package com.chrysoprase.booking.timetable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableDAO {
  private long id;
  private String name;
  private String date;

  public TimeTableDAO(String name, String date){
    this.name = name;
    this.date = date;
  }
}
