package com.deyvidsalvatore.praticaljava.repository;

import com.deyvidsalvatore.praticaljava.entity.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {
}
