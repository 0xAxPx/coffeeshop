package com.coffeeshop.repository;

import com.coffeeshop.model.Coffee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

/**
 * By default, all tests annotated with @DataJpaTest are transactional i.e. all data will be rolled back upon test completion
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CoffeeRepositoryTest {

    /**
     * Purpose of TestEntityManager is to interact with the persistence context
     */
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CoffeeRepository repository;

    @BeforeEach
    public void clearData() {
        repository.deleteAll();
    }

    @Test
    public void testEmptyRecords() {
        List<Coffee> drinks = repository.findAll();
        Assertions.assertThat(drinks).isEmpty();
    }

    @Test
    public void testGetAllCoffeeRecords() {
        repository.save(new Coffee("Cappuccino", "Cappuccino is the base!"));
        repository.save(new Coffee("Doppio", "Doppio"));
        Assertions.assertThat(repository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void testGetCoffeeTypeById() {
        Coffee cappuccino = repository.save(new Coffee("Cappuccino", "Cappuccino is the base!"));
        Coffee doppio = repository.save(new Coffee("Doppio", "Doppio"));
        Assertions.assertThat(repository.findById(3L).get().getId()).isEqualTo(cappuccino.getId());
        Assertions.assertThat(repository.findById(3L).get().getDrink()).isEqualTo(cappuccino.getDrink());
        Assertions.assertThat(repository.findById(3L).get().getDescription()).isEqualTo(cappuccino.getDescription());
        Assertions.assertThat(repository.findById(4L).get().getId()).isEqualTo(doppio.getId());
        Assertions.assertThat(repository.findById(4L).get().getDrink()).isEqualTo(doppio.getDrink());
        Assertions.assertThat(repository.findById(4L).get().getDescription()).isEqualTo(doppio.getDescription());
    }


}