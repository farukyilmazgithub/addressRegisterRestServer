package com.farukyilmaz.ar.repositories;

import com.farukyilmaz.ar.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DistrictRepository extends CrudRepository<District,Long>, JpaRepository<District,Long> {
    ArrayList<District> findDistrictByCity_CityId(Long cityId);
}