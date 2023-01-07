package com.coffeeshop.service;

import com.coffeeshop.model.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CoffeeShopService {
    final private CoffeeRepository repository;

    @Autowired
    public CoffeeShopService(CoffeeRepository repository) {
        this.repository = repository;
    }

    public List<Coffee> findAll() {
        return repository.findAll();
    }

    public Coffee findById(Long id) {
        Optional<Coffee> coffee = repository.findById(id);
        return coffee.orElse(null);
    }

    @Transactional
    public Coffee save(Coffee coffee) {
        Coffee saved = repository.save(coffee);
        System.out.format("Coffee saved : %s\n", saved);
        return saved;
    }

    @Transactional
    public Coffee update(Coffee coffee) {
        System.out.format("Updating coffee:%s\n", coffee);
        Coffee updated = null;
        Coffee toUpdate = findById(coffee.getId());
        if (Objects.nonNull(toUpdate)) {
            toUpdate.setName(coffee.getName());
            toUpdate.setPrice(coffee.getPrice());
            toUpdate.setDescription(coffee.getDescription());
            updated = repository.save(coffee);
        }
        return updated;
    }

    public Coffee findByName(String name) {
        Optional<Coffee> coffee = repository.findAll().stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
        return coffee.orElse(null);
    }

    public Page<Coffee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return repository.findAll(pageable);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    public void delete(Coffee coffee) {
        if (Objects.nonNull(findById(coffee.getId()))) {
            repository.deleteById(coffee.getId());
        }
    }

    public void deleteById(Long id) {
        if (Objects.nonNull(findById(id))) {
            repository.deleteById(id);
        }
    }

    public void noBatchUpdate() {
        long start = System.currentTimeMillis();
        Arrays.asList(createCoffee(1000)).forEach(c -> repository.save(c));
        System.out.format("[Non Batch Update] Total time for save %s is %s ms\n", 1000, System.currentTimeMillis() - start);
    }

    public void batchUpdate() {
        long start = System.currentTimeMillis();
        repository.saveAll(Arrays.asList(createCoffee(1000)));
        System.out.format("[Batch Save] Total time for save %s is %s ms\n", 1000, System.currentTimeMillis() - start);
    }

    private Coffee[] createCoffee(int number) {
        Coffee[] coffees = new Coffee[number];
        for (int i = 0; i < coffees.length; i++) {
            coffees[i] = (new Coffee("Name" + i, "Description" + i, "Address" + i));
        }
        return coffees;
    }
}
