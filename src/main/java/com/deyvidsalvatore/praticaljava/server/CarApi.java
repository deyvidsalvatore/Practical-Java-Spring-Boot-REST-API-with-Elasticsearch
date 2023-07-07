package com.deyvidsalvatore.praticaljava.server;

import com.deyvidsalvatore.praticaljava.entity.Car;
import com.deyvidsalvatore.praticaljava.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/car")
public class CarApi {

    private static final Logger LOG = LoggerFactory.getLogger(CarApi.class);

    private final CarService carService;

    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car random() {
        return carService.generateCar();
    }

    @PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String echo(@RequestBody Car car) {
        LOG.info("Car is {}", car);
        return car.toString();
    }
}
