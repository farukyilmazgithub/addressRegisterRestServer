package com.farukyilmaz.ar.repositories;

import com.farukyilmaz.ar.models.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StreetRepository extends CrudRepository<Street,Long>, JpaRepository<Street,Long> {
    Street findByStreetId(Long streetId);
    ArrayList<Street> findStreetByNeighborhood_NeighborhoodId(Long neighborhoodId);
}