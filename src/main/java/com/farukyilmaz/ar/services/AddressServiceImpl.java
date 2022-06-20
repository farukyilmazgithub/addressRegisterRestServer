package com.farukyilmaz.ar.services;

import com.farukyilmaz.ar.models.Address;
import com.farukyilmaz.ar.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findById(Long Id) {
        return addressRepository.findById(Id).get();
    }

    @Override
    public Address save(Address address) {
        address.setCity(StringUtils.capitalize(address.getCity().toLowerCase()));
        address.setDistrict(StringUtils.capitalize(address.getDistrict().toLowerCase()));
        address.setNeighborhood(StringUtils.capitalize(address.getNeighborhood().toLowerCase()));
        address.setStreet(StringUtils.capitalize(address.getStreet().toLowerCase()));
        address.setAddressLine1(StringUtils.capitalize(address.getAddressLine1().toLowerCase()));
        address.setAddressLine2(StringUtils.capitalize(address.getAddressLine2().toLowerCase()));
        address.setDescription(StringUtils.capitalize(address.getDescription().toLowerCase()));
        return addressRepository.save(address);
    }

    @Override
    public void delete(Address a) {
        addressRepository.delete(a);
    }


    @Override
    public ArrayList<Address> getList() {
        ArrayList<Address> addressList =new ArrayList<>();
        addressRepository.findAll().iterator().forEachRemaining(addressList::add);
        addressList.sort(Comparator.comparing(Address::getAddressId));
        return addressList;
    }

}