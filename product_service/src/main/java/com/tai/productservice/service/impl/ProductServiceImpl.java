package com.tai.productservice.service.impl;

import com.tai.productservice.dto.ProductRequest;
import com.tai.productservice.dto.ProductResponse;
import com.tai.productservice.model.Product;
import com.tai.productservice.repository.ProductRepository;
import com.tai.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> productList =  productRepository.findAll();
        /*List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product p: productList
             ) {
            ProductResponse productResponse = ProductResponse.builder()
                    .name(p.getName())
                    .description(p.getDescription())
                    .price(p.getPrice())
                    .build();
            productResponseList.add(productResponse);
        }
        return productResponseList;*/
        return productList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product p) {
        return ProductResponse.builder()
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build();
    }
}
