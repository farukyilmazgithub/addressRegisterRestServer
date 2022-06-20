package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.City;

import java.util.ArrayList;

public interface CityService {

    City findByCityId(Long cityId);
    ArrayList<City> getList();

}
