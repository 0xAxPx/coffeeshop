package com.coffeeshop.controller;

import com.coffeeshop.service.CoffeeShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch-update")
public class CoffeeBatchController {

    private final CoffeeShopService service;

    @Autowired
    public CoffeeBatchController(CoffeeShopService service) {
        this.service = service;
    }

    @GetMapping()
    public String index() {
        return "batch/index";
    }

    //no batch update
    @GetMapping("/no_batch")
    public String noBatchUpdate() {
        service.noBatchUpdate();
        return "redirect:/";
    }

    //batch update
    @GetMapping("/batch")
    public String batchUpdate() {
        service.batchUpdate();
        return "redirect:/";
    }
}
