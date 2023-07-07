package com.deyvidsalvatore.praticaljava.service.impl;

import com.deyvidsalvatore.praticaljava.entity.Car;
import com.deyvidsalvatore.praticaljava.service.CarService;
import com.deyvidsalvatore.praticaljava.util.RandomDateUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomCarService implements CarService {

    @Override
    public Car generateCar() {
        var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
        var color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
        var type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));

        var available = ThreadLocalRandom.current().nextBoolean();
        var price = ThreadLocalRandom.current().nextInt(5000, 12000);
        var firstReleaseDate = RandomDateUtil.generateRandomLocalDate();

        var result = new Car(brand, color, type);
        result.setAvailable(available);
        result.setPrice(price);
        result.setFirstReleaseDate(firstReleaseDate);

        return result;

    }
}
