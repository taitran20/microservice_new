package com.tai.orderservice.service.impl;


import com.tai.orderservice.dto.InventoryResponse;
import com.tai.orderservice.dto.OrderLineItemsDto;
import com.tai.orderservice.dto.OrderRequest;
import com.tai.orderservice.model.Order;
import com.tai.orderservice.model.OrderLineItems;
import com.tai.orderservice.repository.OrderRepository;
import com.tai.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToEntity).toList();
        order.setOrderLineItems(orderLineItems);
        //Get all skuCode from OrderItemLines
        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode).toList();
        //Call InventoryService, and place if product in stock
        InventoryResponse[] inventoryResponseArray = webClient.get()
                //Tao 1 query check xem skuCode con hang hay khong
                        .uri("http://localhost:8082/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();
        assert inventoryResponseArray != null;
        //System.out.println(Arrays.toString(inventoryResponseArray));
        //Check xem all product order con trong kho hay ko
        boolean allProductInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if (Boolean.TRUE.equals(allProductInStock)){
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }

    }

    private OrderLineItems mapToEntity(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
