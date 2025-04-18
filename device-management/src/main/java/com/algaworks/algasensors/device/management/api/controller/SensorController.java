package com.algaworks.algasensors.device.management.api.controller;

import com.algaworks.algasensors.device.management.api.model.SensorInput;
import com.algaworks.algasensors.device.management.common.IdGenerator;
import com.algaworks.algasensors.device.management.domain.model.Sensor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor create(@RequestBody SensorInput sensorInput) {
        return Sensor.builder()
                .id(IdGenerator.generateTSID())
                .name(sensorInput.name())
                .ip(sensorInput.ip())
                .location(sensorInput.location())
                .protocol(sensorInput.protocol())
                .model(sensorInput.model())
                .enabled(false)
                .build();
    }

}
