package com.swa.stockservice.service;

import com.swa.stockservice.controller.exception.NotFoundException;
import com.swa.stockservice.domain.ProductStock;
import com.swa.stockservice.model.ProductStockDto;
import com.swa.stockservice.repository.ProductStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepository productStockRepository;

    public ProductStockServiceImpl(ProductStockRepository productStockRepository) {
        this.productStockRepository = productStockRepository;
    }

    @Override
    public List<ProductStockDto> findAllProducts() {
        return productStockRepository.findAll()
                .stream()
                .map(productStock -> {
                    ProductStockDto productStockDto = new ProductStockDto();
                    productStockDto.setUpc(productStock.getUpc());
                    productStockDto.setQuantityOnHand(productStock.getQuantityOnHand());

                    //continue implementing

                    return productStockDto;
                }).collect(Collectors.toList());
    }

    @Override
    public ProductStockDto productsByUpc(String upc) {

        return productStockRepository.findByUpc(upc).map(productStock -> {
            ProductStockDto productStockDto = new ProductStockDto();
            productStockDto.setUpc(productStock.getUpc());
            productStockDto.setQuantityOnHand(productStock.getQuantityOnHand());

            return productStockDto;
        }).orElse(null);
    }

    @Override
    public ProductStockDto allocateByUpc(String id, Integer qty) {

        ProductStock product = productStockRepository.findByUpc(id).orElseThrow(NotFoundException::new);

        int newAllocatedQty = product.getQuantityOnHand() + qty;
        product.setQuantityOnHand(newAllocatedQty);

        ProductStock productSaved = productStockRepository.save(product);

        ProductStockDto productStockDto = new ProductStockDto();
        productStockDto.setUpc(productSaved.getUpc());
        productStockDto.setQuantityOnHand(productSaved.getQuantityOnHand());


        return productStockDto;
    }

    @Override
    public ProductStockDto deallocateByUpc(String id, Integer qty) {
        ProductStock product = productStockRepository.findByUpc(id).orElseThrow(NotFoundException::new);

        int newAllocatedQty = product.getQuantityOnHand() - qty;
        product.setQuantityOnHand(newAllocatedQty);

        ProductStock productSaved = productStockRepository.save(product);

        ProductStockDto productStockDto = new ProductStockDto();
        productStockDto.setUpc(productSaved.getUpc());
        productStockDto.setQuantityOnHand(productSaved.getQuantityOnHand());


        return productStockDto;
    }

    @Override
    public ProductStockDto createStock(ProductStockDto stockDto) {
        ProductStock productStock = new ProductStock();
        productStock.setUpc(stockDto.getUpc());
        productStock.setQuantityOnHand(productStock.getQuantityOnHand());

        ProductStock savedStock = productStockRepository.save(productStock);

        ProductStockDto returnedStockDto = new ProductStockDto();
        returnedStockDto.setUpc(savedStock.getUpc());
        returnedStockDto.setQuantityOnHand(savedStock.getQuantityOnHand());

        return returnedStockDto;
    }
}
