package com.farukyilmaz.ar.controllers;

import com.farukyilmaz.ar.models.*;
import com.farukyilmaz.ar.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final AddressService addressService;
    private final CityService cityService;
    private final DistrictService districtService;
    private final NeighborhoodService neighborhoodService;
    private final StreetService streetService;

    @Autowired
    public RestController(AddressService addressService, CityService cityService, DistrictService districtService,
                          NeighborhoodService neighborhoodService, StreetService streetService) {
        this.addressService = addressService;
        this.cityService = cityService;
        this.districtService = districtService;
        this.neighborhoodService = neighborhoodService;
        this.streetService = streetService;
    }

    //city
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public ArrayList<City> getCity() {
        return cityService.getList();
    }

    @RequestMapping(value = "/api/city/{Id}", method = RequestMethod.GET)
    public City getCityByID(@PathVariable Long Id) {
        return cityService.findByCityId(Id);
    }

    //district
    @RequestMapping(value = "/api/city/district/{Id}", method = RequestMethod.GET)
    public ArrayList<District> getDistrictByCity(@PathVariable Long Id) {
        return districtService.getListByCity(Id);
    }

    @RequestMapping(value = "/api/district/{Id}", method = RequestMethod.GET)
    public District getDistrictByID(@PathVariable Long Id) {
        return districtService.findByDistrictId(Id);
    }

    //neighborhood
    @RequestMapping(value = "/api/city/district/neighborhood/{Id}", method = RequestMethod.GET)
    public ArrayList<Neighborhood> getNeighborhoodByDistrict(@PathVariable Long Id) {
        return neighborhoodService.getListByDistrict(Id);
    }

    @RequestMapping(value = "/api/neighborhood/{Id}", method = RequestMethod.GET)
    public Neighborhood getNeighborhoodByID(@PathVariable Long Id) {
        return neighborhoodService.findByNeighborhoodId(Id);
    }

    //street
    @RequestMapping(value = "/api/city/district/neighborhood/street/{Id}", method = RequestMethod.GET)
    public ArrayList<Street> getStreetByNeighborhood(@PathVariable Long Id) {
        return streetService.getListByNeighborhood(Id);
    }

    @RequestMapping(value = "/api/street/{Id}", method = RequestMethod.GET)
    public Street getStreetByID(@PathVariable Long Id) {
        return streetService.findByStreetId(Id);
    }

    //address
    @RequestMapping(value = "/api/address", method = RequestMethod.GET)
    public ArrayList<Address> getAllAddress() {
        return addressService.getList();
    }

    @RequestMapping(value = "/api/address/{Id}", method = RequestMethod.GET)
    public ResponseEntity getAddressById(@PathVariable Long Id) {
        Address address = addressService.findById(Id);
        if( address !=null) return new ResponseEntity<>(address, HttpStatus.OK);
        else return ResponseEntity.notFound().build();

    }

    @RequestMapping(value = "/api/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Address setAddress(@RequestBody Address address) {
        return addressService.save(address);
    }


    @RequestMapping(value = "/api/address/{Id}" ,method = RequestMethod.PUT)
    public ResponseEntity updateAddress(@PathVariable("Id") Long Id, @RequestBody Address updatedAddress){
        try{
            Address address;
            address = addressService.findById(Id);
            System.out.println(address.toString());
            address = updatedAddress;
            addressService.save(address);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/api/address/{Id}" ,method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable("Id") Long Id){
        try{
            Address address = addressService.findById(Id);
            addressService.delete(address);
            return "Address deleted successfully.";
        }
        catch (Exception e)
        {
            return "Address doesn't exist!";
        }
    }
}