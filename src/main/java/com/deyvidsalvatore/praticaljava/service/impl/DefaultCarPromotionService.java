package com.deyvidsalvatore.praticaljava.service.impl;

import com.deyvidsalvatore.praticaljava.service.CarPromotionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarPromotionService implements CarPromotionService {

    @Override
    public boolean isValidPromotionType(String promotionType) {
        return PROMOTION_TYPES.contains(promotionType.toLowerCase());
    }
}
