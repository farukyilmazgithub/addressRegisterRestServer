package com.farukyilmaz.ar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long addressId;
    @NotEmpty(message = "City required")
    private String city;
    @NotEmpty(message = "District required")
    private String district;
    @NotEmpty(message = "Neighborhood required")
    private String neighborhood;
    @NotEmpty(message = "Street required")
    private String street;
    private String addressLine1;
    private String addressLine2;
    private String description;
}
