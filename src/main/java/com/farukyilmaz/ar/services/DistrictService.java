package com.farukyilmaz.ar.services;


import com.farukyilmaz.ar.models.District;

import java.util.ArrayList;


public interface DistrictService {

    District findByDistrictId(Long districtId);
    ArrayList<District> getList();
    ArrayList<District> getListByCity(long cityId);
}
