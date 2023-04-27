package com.eyamattoussi.orderservice.mapper;

import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.dto.InventoryResponseDto;
import com.eyamattoussi.orderservice.model.Order;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto) {
       return null;
    }

    @Override
    public InventoryResponseDto orderToOrderResponseDto(Order order) {
        return null;
    }
}
