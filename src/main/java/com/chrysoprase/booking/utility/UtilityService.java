package com.chrysoprase.booking.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UtilityService {

  private List<Utility> utilities;
  private UtilityRepository utilityRepository;

  @Autowired
  public UtilityService(UtilityRepository utilityRepository) {
    this.utilityRepository = utilityRepository;
  }

  public UtilityService( List<Utility> utilities) {
    this.utilities.add(new Utility("Hairdresser"));
    this.utilities.add(new Utility("Dog-trainer"));
    this.utilities.add(new Utility("Dentist"));
    this.utilities.add(new Utility("Giraffe-feeder"));
  }

  public List<Utility> getUtilities() {
    return utilities;
  }
}
