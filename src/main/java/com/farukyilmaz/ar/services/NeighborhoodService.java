package com.farukyilmaz.ar.services;


import com.farukyilmaz.ar.models.Neighborhood;

import java.util.ArrayList;


public interface NeighborhoodService {

    Neighborhood findByNeighborhoodId(Long neighborhoodId);
    ArrayList<Neighborhood> getList();
    ArrayList<Neighborhood> getListByDistrict(long districtId);
}
