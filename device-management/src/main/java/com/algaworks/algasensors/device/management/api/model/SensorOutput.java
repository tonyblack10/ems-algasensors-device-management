package com.algaworks.algasensors.device.management.api.model;

import io.hypersistence.tsid.TSID;

public record SensorOutput(
        TSID id,
        String name,
        String ip,
        String location,
        String protocol,
        String model,
        Boolean enabled
) {
    public static SensorOutputBuilder builder() {
        return new SensorOutputBuilder();
    }

    public static class SensorOutputBuilder {
        private TSID id;
        private String name;
        private String ip;
        private String location;
        private String protocol;
        private String model;
        private Boolean enabled;

        public SensorOutputBuilder id(TSID id) {
            this.id = id;
            return this;
        }

        public SensorOutputBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SensorOutputBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public SensorOutputBuilder location(String location) {
            this.location = location;
            return this;
        }

        public SensorOutputBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public SensorOutputBuilder model(String model) {
            this.model = model;
            return this;
        }

        public SensorOutputBuilder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public SensorOutput build() {
            return new SensorOutput(id, name, ip, location, protocol, model, enabled);
        }
    }

}
