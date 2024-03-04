package com.tai.productservice.controller;

import com.tai.productservice.dto.ProductRequest;
import com.tai.productservice.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest){
        try {
            productService.createProduct(productRequest);
            return ResponseEntity.ok().body("Success");
        }catch (Exception e){
            return ResponseEntity.ok().body("Failed");
        }
        //return null;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
}
