package com.am.Virtue.repository;

import com.am.Virtue.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<City, Long> {
    public City findCityById(long id);

    public City save(City city);
}
