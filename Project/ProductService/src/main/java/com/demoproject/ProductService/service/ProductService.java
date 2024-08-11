package com.demoproject.ProductService.service;

import com.demoproject.ProductService.model.ProductResponse;
import com.demoproject.ProductService.model.ProductRequest;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
