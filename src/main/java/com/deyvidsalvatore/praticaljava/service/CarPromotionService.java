package com.deyvidsalvatore.praticaljava.service;

import java.util.List;

public interface CarPromotionService {
    List<String> PROMOTION_TYPES = List.of("bonus", "discount");

    boolean isValidPromotionType(String promotionType);
}
