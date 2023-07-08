package com.deyvidsalvatore.praticaljava.server;

import com.deyvidsalvatore.praticaljava.entity.CarPromotion;
import com.deyvidsalvatore.praticaljava.repository.CarElasticRepository;
import com.deyvidsalvatore.praticaljava.repository.CarPromotionElasticRepository;
import com.deyvidsalvatore.praticaljava.service.CarPromotionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/car")
public class CarPromotionApi {

    private final CarPromotionService carPromotionService;

    private final CarPromotionElasticRepository carPromotionElasticRepository;

    public CarPromotionApi(CarPromotionService carPromotionService, CarPromotionElasticRepository carPromotionElasticRepository) {
        this.carPromotionService = carPromotionService;
        this.carPromotionElasticRepository = carPromotionElasticRepository;
    }

    @GetMapping(value = "/promotions")
    public List<CarPromotion> listAvailablePromotions(@RequestParam(name = "type") String promotionType,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        if(!carPromotionService.isValidPromotionType(promotionType)) {
            throw new IllegalArgumentException("Invalid promotion type : " + promotionType);
        }

        var pageable = PageRequest.of(page, size);
        return carPromotionElasticRepository.findByType(promotionType, pageable).getContent();
    }
}
