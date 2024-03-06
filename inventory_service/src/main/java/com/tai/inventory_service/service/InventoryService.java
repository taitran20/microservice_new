package com.tai.inventory_service.service;

import com.tai.inventory_service.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
}
