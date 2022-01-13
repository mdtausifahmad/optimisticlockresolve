package com.example.slowweb;

import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Long> { }
