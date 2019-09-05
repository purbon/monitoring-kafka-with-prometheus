package com.purbon.kafka.clients;

public interface KafkaMonitorBean {

    public void setLatency(Long latency);
    public Long getLatency();

    public void setHealthy(boolean healthy);
    public boolean getHealth();

}
