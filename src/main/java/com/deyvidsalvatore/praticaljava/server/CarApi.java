package com.deyvidsalvatore.praticaljava.server;

import com.deyvidsalvatore.praticaljava.entity.Car;
import com.deyvidsalvatore.praticaljava.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/car")
public class CarApi {

    private final CarService carService;

    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car random() {
        return carService.generateCar();
    }


}
