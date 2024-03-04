package com.tai.orderservice.controller;

import com.tai.orderservice.dto.OrderRequest;
import com.tai.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest){
        try {
            orderService.placeOrder(orderRequest);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Not",HttpStatus.BAD_REQUEST);
        }
    }
}
