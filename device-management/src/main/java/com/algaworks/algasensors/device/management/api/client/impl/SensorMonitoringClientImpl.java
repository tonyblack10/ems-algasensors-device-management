package com.algaworks.algasensors.device.management.api.client.impl;

import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClient;
import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClientBadGatewayException;
import io.hypersistence.tsid.TSID;
import java.time.Duration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

  private final RestClient restClient;

  public SensorMonitoringClientImpl(RestClient.Builder restClientBuilder) {
    this.restClient = restClientBuilder
        .requestFactory(generateClientHttpRequestFactory())
        .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
          throw new SensorMonitoringClientBadGatewayException();
        })
        .build();
  }

  @Override
  public void enableMonitoring(TSID sensorId) {
    restClient
        .put()
        .uri("/api/v1/sensors/{sensorId}/monitoring/enable", sensorId)
        .retrieve()
        .toBodilessEntity();
  }

  @Override
  public void disableMonitoring(TSID sensorId) {
    restClient
        .delete()
        .uri("/api/v1/sensors/{sensorId}/monitoring/enable", sensorId)
        .retrieve()
        .toBodilessEntity();
  }

  private ClientHttpRequestFactory generateClientHttpRequestFactory() {
    var factory = new SimpleClientHttpRequestFactory();

    factory.setReadTimeout(Duration.ofSeconds(5));
    factory.setConnectTimeout(Duration.ofSeconds(3));

    return factory;
  }
}
