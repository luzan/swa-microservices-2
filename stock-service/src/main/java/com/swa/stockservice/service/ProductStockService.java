package com.swa.stockservice.service;

import com.swa.stockservice.domain.ProductStock;
import com.swa.stockservice.model.ProductStockDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ProductStockService {
    List<ProductStockDto> findAllProducts();

    ProductStockDto productsByUpc(String upc);

    ProductStockDto allocateByUpc(String upc, Integer qty);

    ProductStockDto deallocateByUpc(String upc, Integer qty);

    ProductStockDto createStock(ProductStockDto stockDto);
}
