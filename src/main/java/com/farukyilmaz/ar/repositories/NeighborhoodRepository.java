package com.farukyilmaz.ar.repositories;

import com.farukyilmaz.ar.models.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NeighborhoodRepository extends CrudRepository<Neighborhood,Long>, JpaRepository<Neighborhood,Long> {
    Neighborhood findByNeighborhoodId(Long districtId);
    ArrayList<Neighborhood> findNeighborhoodByDistrict_DistrictId(Long districtId);
}