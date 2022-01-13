package com.example.slowweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @OptimisticLockSafe
    public Product findOne(Long id) {
        throw new PessimisticLockingFailureException("PessimisticLockingFailureException");
    }
}
