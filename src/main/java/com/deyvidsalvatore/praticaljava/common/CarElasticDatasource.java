package com.deyvidsalvatore.praticaljava.common;

import com.deyvidsalvatore.praticaljava.entity.Car;
import com.deyvidsalvatore.praticaljava.repository.CarElasticRepository;
import com.deyvidsalvatore.praticaljava.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Component
public class CarElasticDatasource {

    private static final Logger LOG = LoggerFactory.getLogger(CarElasticDatasource.class);

    private final CarElasticRepository carElasticRepository;

    @Qualifier("randomCarService")
    private final CarService carService;

    @Qualifier("webClientElasticsearch")
    private final WebClient webClient;

    public CarElasticDatasource(CarElasticRepository carElasticRepository, CarService carService, WebClient webClient) {
        this.carElasticRepository = carElasticRepository;
        this.carService = carService;
        this.webClient = webClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateDate() {
        var response = webClient.delete().uri("/practical-java").retrieve().bodyToMono(String.class).block();
        LOG.info("End delete with response: {}", response);

        var cars = new ArrayList<Car>();

        for (int i = 0; i < 10_000; i++) {
            cars.add(carService.generateCar());
        }

        carElasticRepository.saveAll(cars);

        LOG.info("Saved {} cars", carElasticRepository.count());
    }

}
