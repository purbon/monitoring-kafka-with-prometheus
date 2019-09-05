package com.purbon.kafka.clients;

public class LatencyReporter implements KafkaMonitorBean {

  private long latency;
  @Override
  public void setLatency(Long latency) {
    this.latency = latency;
  }

  @Override
  public Long getLatency() {
    return latency;
  }
}
