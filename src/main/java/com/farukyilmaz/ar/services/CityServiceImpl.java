package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.City;
import com.farukyilmaz.ar.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findByCityId(Long cityId) {
        return cityRepository.findById(cityId).get();
    }

    @Override
    public ArrayList<City> getList() {
        ArrayList<City> cityList = new ArrayList<>();
        cityRepository.findAll().iterator().forEachRemaining(cityList::add);
        cityList.sort(Comparator.comparing(City::getCityId));
        return cityList;
    }


}
