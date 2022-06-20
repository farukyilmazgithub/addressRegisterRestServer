package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.Neighborhood;
import com.farukyilmaz.ar.repositories.NeighborhoodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodServiceImpl(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public Neighborhood findByNeighborhoodId(Long neighborhoodId) {
        return neighborhoodRepository.findByNeighborhoodId(neighborhoodId);
    }

    @Override
    public ArrayList<Neighborhood> getList() {
        ArrayList<Neighborhood> neighborhoodList =new ArrayList<>();
        neighborhoodRepository.findAll().iterator().forEachRemaining(neighborhoodList::add);
        neighborhoodList.sort(Comparator.comparing(Neighborhood::getNeighborhoodId));
        return neighborhoodList;
    }

    @Override
    public ArrayList<Neighborhood> getListByDistrict(long districtId) {
        ArrayList<Neighborhood> neighborhoodList =new ArrayList<>();
        neighborhoodRepository.findNeighborhoodByDistrict_DistrictId(districtId).iterator().forEachRemaining(neighborhoodList::add);
        return neighborhoodList;
    }


}
