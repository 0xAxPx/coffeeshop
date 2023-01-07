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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CoffeeShopServiceTests {

    private CoffeeShopService service;

    @Mock
    private CoffeeRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new CoffeeShopService(repository);
    }

    @Test
    public void testSaveCoffeeDrink() {
        Coffee cap = new Coffee("Cap", "1.4", "Cap Desc");
        when(repository.save(cap)).thenReturn(cap);

        //Test
        Coffee coffee = service.save(cap);
        Assertions.assertEquals(coffee, cap);
        verify(repository, times(1)).save(coffee);
    }

    @Test
    public void testFindAll() {
        List<Coffee> coffeeList = new ArrayList<>(5);
        coffeeList.add(new Coffee("Cap", "1.4", "Cap Desc"));
        coffeeList.add(new Coffee("Mock", "1", "Mock Address"));
        coffeeList.add(new Coffee("Amer", "1", "Amer Address"));
        coffeeList.add(new Coffee("Double", "1", "Double Address"));
        coffeeList.add(new Coffee("Ice", "1", "Ice Address"));

        when(repository.findAll()).thenReturn(coffeeList);

        //Test
        List<Coffee> store = service.findAll();
        Assertions.assertEquals(5, store.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdateCoffee() {
        Coffee cap = new Coffee(1L,"Cap", "1.4", "Cap Desc");
        when(repository.save(any(Coffee.class))).thenReturn(cap);
        when(repository.findById(cap.getId())).thenReturn(Optional.of(cap));

        //Test
        final Coffee coffee = service.update(cap);
        Assertions.assertEquals(coffee, cap);
        verify(repository).save(any(Coffee.class));
    }

    @Test
    public void testFindById() {
        Coffee cap = new Coffee("Cap", "1.4", "Cap Description");
        when(repository.findById(cap.getId())).thenReturn(Optional.of(cap));

        //Test
        Coffee coffee = service.findById(cap.getId());
        Assertions.assertEquals(coffee.getPrice(), cap.getPrice());
        Assertions.assertEquals(coffee.getDescription(), cap.getDescription());
        verify(repository, times(1)).findById(coffee.getId());
    }

    @Test
    public void testDeleteAll() {
        doNothing().when(repository).deleteAll();
        repository.deleteAll();
        verify(repository, times(1)).deleteAll();
    }

    @Test
    public void testDeleteNullCoffee() {
        Coffee coffee = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
                doThrow(NullPointerException.class).when(repository).delete(coffee);
                repository.delete(coffee);        }
        );
    }

    @Test
    public void testDeleteCoffee() {
        Coffee coffee = new Coffee("Cap", "1.4", "Cap Desc");
        doNothing().when(repository).delete(coffee);
        repository.delete(coffee);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        repository.deleteById(id);
        verify(repository, times(1)).deleteById(id);
    }

}