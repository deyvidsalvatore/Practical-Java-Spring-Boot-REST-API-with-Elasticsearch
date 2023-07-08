package com.deyvidsalvatore.praticaljava.repository;

import com.deyvidsalvatore.praticaljava.entity.CarPromotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPromotionElasticRepository extends ElasticsearchRepository<CarPromotion, String> {

    public Page<CarPromotion> findByType(String type, Pageable pageable);
}
