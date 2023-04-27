package com.eyamattoussi.orderservice.service;

import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.dto.InventoryResponseDto;

import java.util.List;

public interface OrderService {
    InventoryResponseDto getOrderById(String id);
    List<InventoryResponseDto> getOrders();
    InventoryResponseDto addOrder(OrderRequestDto orderRequestDto);
    InventoryResponseDto updateOrder(String id, OrderRequestDto orderRequestDto);
    void deleteOrder(String id);
}
