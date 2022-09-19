package com.swa.stockservice.repository;

import com.swa.stockservice.domain.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    Optional<ProductStock> findByUpc (String upc);
}
