package com.farukyilmaz.ar.repositories;


import com.farukyilmaz.ar.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long>, JpaRepository<Address,Long> {
}