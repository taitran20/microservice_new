package com.tai.orderservice.service;

import com.tai.orderservice.dto.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}
