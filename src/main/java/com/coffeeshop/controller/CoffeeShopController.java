package com.coffeeshop.controller;


import com.coffeeshop.persistence.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoffeeShopController {

    @Autowired
    CoffeeRepository repository;

    @GetMapping("/coffee")
    public ResponseEntity<List<Coffee>> getCoffeeAll() {
        List<Coffee> coffee = repository.findAll();
        if (!coffee.isEmpty()) {
            return new ResponseEntity<List<Coffee>>(coffee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/coffee/{id}")
    public ResponseEntity<Coffee> getCoffeeByType(@PathVariable("id") long id) {
        Optional<Coffee> coffee = repository.findById(id);
        if (coffee.isPresent()) {
            return new ResponseEntity(coffee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
