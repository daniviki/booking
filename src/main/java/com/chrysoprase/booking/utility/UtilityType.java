package com.chrysoprase.booking.utility;

public enum UtilityType {
  HAIRDRESSER {
    public Utility generateUtility() {
      return new Hairdreser();
    }
  },
  DENTIST{
    public Utility generateUtility() {
      return new Dentist();
    }
  },
  DOG_TRAINER{
    public Utility generateUtility() {
      return new DogTrainer();
    }
  },
  BABY_SITTER{
    public Utility generateUtility() {
      return new BabySitter();
    }
  },
  GIRAFFE_FEEDER{
    public Utility generateUtility() {
      return new GiraffeFeeder();
    }
  }
  public Utility generateUtility() {
    return null;
  }
}
