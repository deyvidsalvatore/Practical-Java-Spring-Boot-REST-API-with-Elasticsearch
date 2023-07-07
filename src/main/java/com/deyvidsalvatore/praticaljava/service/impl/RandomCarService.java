package com.deyvidsalvatore.praticaljava.service.impl;

import com.deyvidsalvatore.praticaljava.entity.Car;
import com.deyvidsalvatore.praticaljava.service.CarService;
import com.deyvidsalvatore.praticaljava.util.RandomDateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        var randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURES.size());
        var additionalFeatures = new ArrayList<String>();

        for (int i = 0; i < randomCount; i++) {
            additionalFeatures.add(ADDITIONAL_FEATURES.get(i));
        }


        var result = new Car(brand, color, type);
        result.setAvailable(available);
        result.setPrice(price);
        result.setFirstReleaseDate(firstReleaseDate);
        result.setAdditionalFeatures(additionalFeatures);

        return result;

    }
}
