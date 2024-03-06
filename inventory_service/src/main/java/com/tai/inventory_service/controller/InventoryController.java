package com.tai.inventory_service.controller;

import com.tai.inventory_service.dto.InventoryResponse;
import com.tai.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping()
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCode){
        try {
            return new ResponseEntity<>(inventoryService.isInStock(skuCode), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
