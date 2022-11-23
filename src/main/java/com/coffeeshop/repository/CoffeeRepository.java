package com.coffeeshop.repository;


import com.coffeeshop.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * In JPA we have CRUDRepository and JPARepository
 * CrudRepository interface mainly provides CRUD operations
 * JPARepository extends %PagingAndSortingRepository% and provides extra CRUD methods (e.g. flush()) and methods to implement pagination (divide a large number of records into chunks)
 */

@Repository
@Transactional
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {


}
