package com.farukyilmaz.ar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class District {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long districtId;
    private String districtName;
    @ManyToOne
    private City city;
}
