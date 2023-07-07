package com.deyvidsalvatore.praticaljava.server;

import com.deyvidsalvatore.praticaljava.entity.Car;
import com.deyvidsalvatore.praticaljava.repository.CarElasticRepository;
import com.deyvidsalvatore.praticaljava.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/api/v1/car")
public class CarApi {

    private static final Logger LOG = LoggerFactory.getLogger(CarApi.class);

    private final CarService carService;

    private final CarElasticRepository carElasticRepository;

    public CarApi(CarService carService, CarElasticRepository carElasticRepository) {
        this.carService = carService;
        this.carElasticRepository = carElasticRepository;
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

    @GetMapping(value = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> randomCars() {
        var result = new ArrayList<Car>();

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 10); i++) {
            result.add(carService.generateCar());
        }

        return result;
    }

    @GetMapping(value = "/count")
    public String countCar() {
        return "There are : " + carElasticRepository.count() + " cars";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCar(@RequestBody Car car) {
        var id = carElasticRepository.save(car).getId();
        return "Saved car with ID: " + id;
    }

    @GetMapping(value = "/{id}")
    public Car getCar(@PathVariable("id") String id) {
        return carElasticRepository.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public String updateCar(@PathVariable("id") String carId, @RequestBody Car updatedCar) {
        updatedCar.setId(carId);
        var newCar = carElasticRepository.save(updatedCar);
        return "Updated car with ID: " + newCar.getId();
    }

    @GetMapping(value = "/find-json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> findByCarsByBrandAndColor(@RequestBody Car car,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size, Sort.Direction.DESC);
        return carElasticRepository.findByBrandAndColor(car.getBrand(), car.getColor(), pageable).getContent();
    }

    @GetMapping(value = "/cars/{brand}/{color}")
    public List<Car> findCarsByPath(@PathVariable String brand, @PathVariable String color,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        return carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
    }

    @GetMapping(value = "/cars")
    public List<Car> findCarsByParam(@RequestParam String brand, @RequestParam String color,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);
        return carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
    }

    @GetMapping(value = "/cars/date")
    public List<Car> findCarsReleasedAfter(@RequestParam(name = "first_release_date")
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstReleaseDate) {
        return carElasticRepository.findByFirstReleaseDateAfter(firstReleaseDate);
    }

}
