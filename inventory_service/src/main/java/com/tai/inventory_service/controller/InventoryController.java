package com.tai.inventory_service.controller;

import com.tai.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping("/{sku_code}")
    public ResponseEntity<Object> isInStock(@PathVariable("sku_code") String skuCode){
        try {
            inventoryService.isInStock(skuCode);
            return ResponseEntity.ok().body("OK");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("NOT");
        }
    }
}
