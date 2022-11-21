package com.coffeeshop.model;


import lombok.Data;

import javax.persistence.*;

/**
 * Hibernate and Spring/JPA needs to have default constructor,
 * because when Hibernate/Spring creates an instance of entity using reflection it calls Class.newInstance() method which requires no-arg constructor
 */

@Data
@Entity
@Table(name = "coffee_drinks")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "coffee_drink")
    String drink;
    @Column(name = "description")
    String description;

    public Coffee(String drink, String description) {
        this.drink = drink;
        this.description = description;
    }

    public Coffee() {};

}
