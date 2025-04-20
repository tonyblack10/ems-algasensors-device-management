package com.algaworks.algasensors.device.management.api.model;

public record SensorDetailOutput(
    SensorOutput sensor,
    SensorMonitoringOutput monitoring
) {

  public static SensorDetailOutputBuilder builder() {
    return new SensorDetailOutputBuilder();
  }

  public static class SensorDetailOutputBuilder {

    private SensorOutput sensor;
    private SensorMonitoringOutput monitoring;

    public SensorDetailOutputBuilder sensor(SensorOutput sensor) {
      this.sensor = sensor;
      return this;
    }

    public SensorDetailOutputBuilder monitoring(SensorMonitoringOutput monitoring) {
      this.monitoring = monitoring;
      return this;
    }

    public SensorDetailOutput build() {
      return new SensorDetailOutput(sensor, monitoring);
    }
  }
}
