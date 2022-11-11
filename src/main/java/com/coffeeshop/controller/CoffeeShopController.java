package com.coffeeshop.controller;


import com.coffeeshop.persistence.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class CoffeeShopController {

    @Autowired
    CoffeeRepository repository;

    @GetMapping("/")
    private String indexPage() {
        return "Coffee Shop\t" + LocalDateTime.now();
    }

    @GetMapping("/coffee")
    public ResponseEntity<List<Coffee>> getCoffeeList(Model model) {
        List<Coffee> coffee = repository.findAll();
        if (!coffee.isEmpty()) {
            model.addAttribute("list", coffee);
            return new ResponseEntity<List<Coffee>>(coffee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/coffee/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable("id") long id, Model model) {
        Optional<Coffee> coffee = repository.findById(id);
        if (coffee.isPresent()) {
            model.addAttribute(String.valueOf(id), coffee);
            return new ResponseEntity(coffee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
