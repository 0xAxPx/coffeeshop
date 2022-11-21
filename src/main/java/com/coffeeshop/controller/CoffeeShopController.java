package com.coffeeshop.controller;


import com.coffeeshop.model.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CoffeeShopController {

    @Autowired
    CoffeeRepository repository;

    @GetMapping("/")
    private String home() {
        return "redirect:coffee/show";
    }

    @GetMapping("/coffee/show")
    private String index(Model model, Coffee coffee) {
        model.addAttribute("message", "This is Template example!");
        List<Coffee> coffeeList = repository.findAll();
        if (!coffeeList.isEmpty()) {
            model.addAttribute("coffee_drinks", coffeeList);
        }
        return "show";
    }

    @GetMapping("/coffee/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("coffee", repository.findById(id));
        return "coffee";
    }

    @GetMapping("/coffee")
    public String newCoffee(Model model) {
        model.addAttribute("coffee", new Coffee());
        return "coffee";
    }

    @PostMapping("/coffee")
    public String addCoffee(@ModelAttribute("coffee") Coffee coffee) {
        System.out.format("%s - coffee object %s\n", LocalDateTime.now(), coffee.toString());
        repository.save(coffee);
        return "redirect:coffee/show";
    }

}
