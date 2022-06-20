package com.farukyilmaz.ar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Neighborhood {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long neighborhoodId;
    private String neighborhoodName;
    @ManyToOne
    private District district;
}
