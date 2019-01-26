package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
