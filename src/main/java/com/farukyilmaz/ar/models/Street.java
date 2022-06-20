package com.farukyilmaz.ar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Street {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long streetId;
    private String streetName;
    @ManyToOne
    private Neighborhood neighborhood;
}
