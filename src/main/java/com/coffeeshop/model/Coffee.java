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
@Table(name = "beverages", schema = "barista_owner")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Beverage can't be empty")
    @Size(min = 5, max = 30)
    @Column(name = "beverage")
    private String name;

    @NotEmpty(message = "Price can't be empty")
    @Size(min = 5, max = 30)
    @Column(name = "price")
    private String price;

    @NotEmpty(message = "Description can't be empty")
    @Size(min = 5, max = 30)
    @Column(name = "description")
    private String description;


    public Coffee(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Coffee(Long id, String name, String price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Coffee() {}

}
