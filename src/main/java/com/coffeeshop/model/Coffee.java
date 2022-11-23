package com.coffeeshop.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Hibernate and Spring/JPA needs to have default constructor,
 * because when Hibernate/Spring creates an instance of entity using reflection it calls Class.newInstance() method which requires no-arg constructor
 */

@Data
@Entity
@Table(name = "coffee_drinks", schema = "barista_owner")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Coffee type can't be empty")
    @Size(min = 5, max = 30)
    @Column(name = "coffee_drink")
    String drink;

    @NotEmpty(message = "Coffee description can't be empty")
    @Size(min = 5, max = 256)
    @Column(name = "description")
    String description;

    public Coffee(String drink, String description) {
        this.drink = drink;
        this.description = description;
    }

    public Coffee() {};

}
