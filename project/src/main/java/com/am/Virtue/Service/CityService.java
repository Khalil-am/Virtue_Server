package com.am.Virtue.Service;

import com.am.Virtue.entities.City;

public interface CityService {
    public City findCityById(long id);

    public City createCity(City city);

}
