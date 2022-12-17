package com.coffeeshop.service;

import org.springframework.data.domain.Page;

public interface IShopService<T> {

    T findById(Long id);

    T findByName(String name);

    Page<T> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    void deleteAll();
}
