package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.Neighborhood;
import com.farukyilmaz.ar.models.Street;
import com.farukyilmaz.ar.repositories.StreetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    @Override
    public Street findByStreetId(Long streetId) {
        return streetRepository.findByStreetId(streetId);
    }

    @Override
    public ArrayList<Street> getList() {
        ArrayList<Street> streetList =new ArrayList<>();
        streetRepository.findAll().iterator().forEachRemaining(streetList::add);
        streetList.sort(Comparator.comparing(Street::getStreetId));
        return streetList;
    }

    @Override
    public ArrayList<Street> getListByNeighborhood(long neighborhoodId) {
        ArrayList<Street> streetList =new ArrayList<>();
        streetRepository.findStreetByNeighborhood_NeighborhoodId(neighborhoodId).iterator().forEachRemaining(streetList::add);
        return streetList;
    }

}
