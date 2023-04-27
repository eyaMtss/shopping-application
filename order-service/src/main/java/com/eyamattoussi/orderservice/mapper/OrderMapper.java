package com.eyamattoussi.orderservice.mapper;

import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.dto.InventoryResponseDto;
import com.eyamattoussi.orderservice.model.Order;

public interface OrderMapper {
    Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto);
    InventoryResponseDto orderToOrderResponseDto(Order order);
}
