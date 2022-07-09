package com.coffeeshop.persistence;


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
    String coffee;
    @Column(name = "description")
    String description;

    public Coffee(String coffee, String description) {
        this.coffee = coffee;
        this.description = description;
    }

    protected Coffee() {};

}
