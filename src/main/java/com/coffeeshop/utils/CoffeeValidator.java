package com.coffeeshop.utils;


import com.coffeeshop.model.Coffee;
import com.coffeeshop.service.CoffeeShopService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

@Component
public class CoffeeValidator implements Validator {

    private final CoffeeShopService service;

    @Autowired
    public CoffeeValidator(CoffeeShopService service) {
        this.service = service;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Coffee.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NonNull Errors errors) {
        Coffee coffee = (Coffee) target;
        if (service.findByName(coffee.getName()) != null ) {
            errors.rejectValue("name", "", "This coffee drink is already added");
        }
    }
}
