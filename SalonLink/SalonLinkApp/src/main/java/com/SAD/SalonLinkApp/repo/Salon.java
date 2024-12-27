package com.SAD.SalonLinkApp.repo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "Salons")

public class Salon {


    @SequenceGenerator(
            name = "salon_sequence",
            sequenceName = "salon_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "salon_sequence"
    )
    private Long id;
    private String name;
    private String address;
    private String services;


    public Salon(String name,
                 String address,
                 String services) {
        this.name = name;
        this.address = address;
        this.services = services;
    }
}