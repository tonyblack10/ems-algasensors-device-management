package com.algaworks.algasensors.device.management.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

  @Bean
  public SensorMonitoringClient sensorMonitoringClient(RestClientFactory factory) {
    var restClient = factory.temperatureMonitoringRestClient();

    var restClientAdapter = RestClientAdapter.create(restClient);
    var proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

    return proxyFactory.createClient(SensorMonitoringClient.class);
  }

}
