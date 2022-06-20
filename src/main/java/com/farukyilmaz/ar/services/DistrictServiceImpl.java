package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.District;
import com.farukyilmaz.ar.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public District findByDistrictId(Long districtId) {
        return districtRepository.findByDistrictId(districtId);
    }

    @Override
    public ArrayList<District> getList() {
        ArrayList<District> districtList =new ArrayList<>();
        districtRepository.findAll().iterator().forEachRemaining(districtList::add);
        districtList.sort(Comparator.comparing(District::getDistrictId));
        return districtList;
    }

    @Override
    public ArrayList<District> getListByCity(long cityID) {
        ArrayList<District> districtList =new ArrayList<>();
        districtRepository.findDistrictByCity_CityId(cityID).iterator().forEachRemaining(districtList::add);
        return districtList;
    }

}
