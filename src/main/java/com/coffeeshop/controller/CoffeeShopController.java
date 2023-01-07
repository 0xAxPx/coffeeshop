package com.coffeeshop.controller;

import com.coffeeshop.model.Coffee;
import com.coffeeshop.service.CoffeeShopService;
import com.coffeeshop.utils.CoffeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
public class CoffeeShopController {

   private final CoffeeShopService service;
   private final CoffeeValidator validator;

   @Autowired
   public CoffeeShopController(CoffeeShopService service, CoffeeValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/showNewForm")
    public String showNewCoffeeForm(Model model) {
        // create model attribute to bind form data
        Coffee coffee = new Coffee();
        model.addAttribute("coffee", coffee);
        return "new_coffee";
    }

    @PostMapping("/saveCoffee")
    public String saveCoffee(@ModelAttribute("coffee") @Valid Coffee coffee, BindingResult bindingResult) {
       validator.validate(coffee, bindingResult);
       if (bindingResult.hasErrors()) return "/new_coffee";
       service.save(coffee);
       return "redirect:/";
    }

    @PostMapping("/updateCoffee")
    public String updateCoffee(@ModelAttribute("coffee") @Valid Coffee coffee, BindingResult bindingResult) {
        validator.validate(coffee, bindingResult);
        if (bindingResult.hasErrors()) return "/update_coffee";
        service.update(coffee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
       Coffee coffee = service.findById(id);
        model.addAttribute("coffee", coffee);
        return "update_coffee";
    }

    @GetMapping("/deleteCoffee/{id}")
    public String deleteCoffee(@PathVariable (value = "id") long id) {
        Coffee coffee = service.findById(id);
        service.delete(coffee);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Coffee> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Coffee> listCoffee = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listCoffee", listCoffee);
        return "index";
    }
}
