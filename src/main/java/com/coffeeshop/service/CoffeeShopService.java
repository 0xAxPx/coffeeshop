package com.coffeeshop.service;

import com.coffeeshop.model.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeShopService implements IShopService<Coffee> {
    private CoffeeRepository repository;

    @Autowired
    public CoffeeShopService(CoffeeRepository repository) {
        this.repository = repository;
    }

    public Coffee save(Coffee coffee) {
        Coffee saved = repository.save(coffee);
        System.out.format("Coffee saved : %s\n", saved.toString());
        return saved;
    }

    public List<Coffee> findAll() {
        return repository.findAll();
    }

    public Coffee findById(Long id) {
        Optional<Coffee> coffee = repository.findById(id);
        return coffee.orElse(null);
    }

    @Override
    public Page<Coffee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return repository.findAll(pageable);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
