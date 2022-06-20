package com.farukyilmaz.ar.services;


import com.farukyilmaz.ar.models.Neighborhood;
import com.farukyilmaz.ar.models.Street;

import java.util.ArrayList;


public interface StreetService {
    Street findByStreetId(Long streetId);
    ArrayList<Street> getList();
    ArrayList<Street> getListByNeighborhood(long neighborhoodId);
}
