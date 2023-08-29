package com.example.restapiproduct.repository;

import com.example.restapiproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByCode(String code);
    boolean existsByCode(String code);

    @Transactional
    void  deleteByCode(String code);
}
