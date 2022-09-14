package com.am.Virtue.Service.impl;

import com.am.Virtue.Service.CityService;
import com.am.Virtue.entities.City;
import com.am.Virtue.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepo cityRepo;


    @Override
    public City findCityById(long id) {
        return cityRepo.findCityById(id);
    }

    @Override
    public City createCity(City city) {
        return cityRepo.save(city);
    }
}
