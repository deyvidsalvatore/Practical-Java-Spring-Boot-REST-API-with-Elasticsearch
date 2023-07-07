package com.deyvidsalvatore.praticaljava.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateUtil {

    private static final long MIN_DAY = LocalDate.of(2010, Month.JANUARY, 1).toEpochDay();

    private static final long MAX_DAY = LocalDate.now().toEpochDay();

    public static LocalDate generateRandomLocalDate() {
        long randomDay = MIN_DAY + ThreadLocalRandom.current().nextLong(MAX_DAY - MIN_DAY);
        return LocalDate.ofEpochDay(randomDay);
    }
}
