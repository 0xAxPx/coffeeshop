package com.coffeeshop.service;

import org.springframework.data.domain.Page;

public interface IShopService<T> {

    T findById(Long id);

    Page<T> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
