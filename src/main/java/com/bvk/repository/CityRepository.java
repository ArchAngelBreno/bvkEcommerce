package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long>{

}
