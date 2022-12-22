package com.coffeeshop.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Coffee type can't be empty")
    @Size(min = 5, max = 30)
    @Column(name = "coffee_drink")
    private String drink;

    @NotEmpty(message = "Coffee description can't be empty")
    @Size(min = 5, max = 256)
    @Column(name = "description")
    private String description;

    //Country, City of delivery
    @Pattern(regexp = "[A-Z]\\+w", message = "Address should be in 'Country, City'")
    private String address;

    public Coffee(String drink, String description, String address) {
        this.drink = drink;
        this.description = description;
        this.address = address;
    }

    public Coffee(Long id, String drink, String description, String address) {
        this.id = id;
        this.drink = drink;
        this.description = description;
        this.address = address;
    }

    public Coffee() {};

}
