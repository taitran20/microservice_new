package com.tai.productservice.service;

import com.tai.productservice.dto.ProductRequest;
import com.tai.productservice.dto.ProductResponse;
import com.tai.productservice.model.Product;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
