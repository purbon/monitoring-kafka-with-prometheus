package com.purbon.kafka.clients;

public class CarEvent {


  public Long viewtime;
  public String plate;
  public String country;

  public CarEvent() {

  }

  public CarEvent(Long viewtime, String plate, String country) {

    this.viewtime = viewtime;
    this.plate = plate;
    this.country = country;
  }

  public CarEvent(Long viewtime, String plate) {
    this(viewtime, plate, "");
  }

  public Long getViewtime() {
    return viewtime;
  }

  public String getPlate() {
    return plate;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
