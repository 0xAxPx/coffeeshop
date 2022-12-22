package com.coffeeshop.service;

import com.coffeeshop.model.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CoffeeShopServiceTest {

    @InjectMocks
    private CoffeeShopService service;

    @Mock
    private CoffeeRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Coffee> coffeeList = new ArrayList<>(5);
        coffeeList.add(new Coffee("Cap", "Cap Desc", "Cap Address"));
        coffeeList.add(new Coffee("Mock", "Mock Desc", "Mock Address"));
        coffeeList.add(new Coffee("Amer", "Amer Desc", "Amer Address"));
        coffeeList.add(new Coffee("Double", "Double Desc", "Double Address"));
        coffeeList.add(new Coffee("Ice", "Ice Desc", "Ice Address"));

        when(repository.findAll()).thenReturn(coffeeList);

        //Test
        List<Coffee> store = service.findAll();
        Assertions.assertEquals(5, store.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testCreateCoffee() {
        Coffee cap = new Coffee("Cap", "Cap Desc", "Cap Address");

        when(repository.save(cap)).thenReturn(cap);

        //Test
        Coffee coffee = service.save(cap);
        Assertions.assertEquals(coffee, cap);
        verify(repository, times(1)).save(coffee);
    }

    @Test
    public void testFindByName() {
        Coffee cap = new Coffee("Cap", "Cap Desc", "Cap Address");

        when(repository.findById(cap.getId())).thenReturn(Optional.of(cap));

        //Test
        Coffee coffee = service.findById(cap.getId());
        Assertions.assertEquals(coffee.getAddress(), cap.getAddress());
        verify(repository, times(1)).findById(coffee.getId());
    }

}