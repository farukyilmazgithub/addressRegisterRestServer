package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.Address;

import java.util.ArrayList;

public interface AddressService {
    ArrayList<Address> getList();
    Address findById(Long Id);
    Address save(Address address);
    void delete(Address address);
}
