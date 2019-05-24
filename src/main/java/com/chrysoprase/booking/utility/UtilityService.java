package com.chrysoprase.booking.utility;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

  private UtilityRepository utilityRepository;

  public UtilityService(UtilityRepository utilityRepository) {
    this.utilityRepository = utilityRepository;
  }

  public void addUtility(Utility utility) {
    utilityRepository.save(utility);
  }
}
