package com.chrysoprase.booking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@PropertySource(value = "classpath:application-test.properties")
public class BookingApplicationTests {

  @Test
  public void contextLoads() {
  }

}
