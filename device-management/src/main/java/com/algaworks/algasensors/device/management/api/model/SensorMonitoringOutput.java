package com.algaworks.algasensors.device.management.api.model;

import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;

public record SensorMonitoringOutput(
    TSID id,
    Double lastTemperature,
    OffsetDateTime updatedAt,
    Boolean enabled
) {

  public static SensorMonitoringOutputBuilder builder() {
    return new SensorMonitoringOutputBuilder();
  }

  public static class SensorMonitoringOutputBuilder {

    private TSID id;
    private Double lastTemperature;
    private OffsetDateTime updatedAt;
    private Boolean enabled;

    public SensorMonitoringOutputBuilder id(TSID id) {
      this.id = id;
      return this;
    }

    public SensorMonitoringOutputBuilder lastTemperature(Double lastTemperature) {
      this.lastTemperature = lastTemperature;
      return this;
    }

    public SensorMonitoringOutputBuilder updatedAt(OffsetDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public SensorMonitoringOutputBuilder enabled(Boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public SensorMonitoringOutput build() {
      return new SensorMonitoringOutput(id, lastTemperature, updatedAt, enabled);
    }
  }
}
