package com.eyamattoussi.orderservice.service;

import com.eyamattoussi.orderservice.dto.OrderLineItemsDto;
import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.model.Order;
import com.eyamattoussi.orderservice.model.OrderLineItems;
import com.eyamattoussi.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(OrderRequestDto orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        return orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
