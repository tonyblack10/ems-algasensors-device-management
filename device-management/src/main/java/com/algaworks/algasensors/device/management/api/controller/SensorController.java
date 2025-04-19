package com.algaworks.algasensors.device.management.api.controller;

import com.algaworks.algasensors.device.management.api.model.SensorInput;
import com.algaworks.algasensors.device.management.api.model.SensorOutput;
import com.algaworks.algasensors.device.management.common.IdGenerator;
import com.algaworks.algasensors.device.management.domain.model.Sensor;
import com.algaworks.algasensors.device.management.domain.model.SensorId;
import com.algaworks.algasensors.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping
    public Page<SensorOutput> search(@PageableDefault Pageable pageable) {
        var sensorsPage = sensorRepository.findAll(pageable);

        return sensorsPage.map(this::convertToModel);
    }

    @GetMapping("/{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.convertToModel(sensor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput sensorInput) {
        var sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(sensorInput.name())
                .ip(sensorInput.ip())
                .location(sensorInput.location())
                .protocol(sensorInput.protocol())
                .model(sensorInput.model())
                .enabled(false)
                .build();

        sensor = sensorRepository.saveAndFlush(sensor);

        return convertToModel(sensor);
    }

    @PutMapping("/{sensorId}")
    public SensorOutput update(@PathVariable TSID sensorId, @RequestBody SensorInput sensorInput) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensor.setName(sensorInput.name());
        sensor.setIp(sensorInput.ip());
        sensor.setLocation(sensorInput.location());
        sensor.setProtocol(sensorInput.protocol());
        sensor.setModel(sensorInput.model());

        sensor = sensorRepository.saveAndFlush(sensor);

        return convertToModel(sensor);
    }

    @DeleteMapping("/{sensorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        var sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensorRepository.delete(sensor);
    }

    private SensorOutput convertToModel(Sensor sensor) {
        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .build();
    }

}
