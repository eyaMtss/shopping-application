package com.eyamattoussi.orderservice.service;

import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.dto.InventoryResponseDto;
import com.eyamattoussi.orderservice.mapper.OrderMapperImpl;
import com.eyamattoussi.orderservice.model.Order;
import com.eyamattoussi.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private OrderMapperImpl orderMapper = new OrderMapperImpl();
    @Override
    public InventoryResponseDto getOrderById(String id) {
        log.info("Getting a product");
        return orderRepository.findById(id).isPresent() ?
                orderMapper.orderToOrderResponseDto(orderRepository.findById(id).get()) : null;
    }

    @Override
    public List<InventoryResponseDto> getOrders() {
        log.info("Getting all orders ...");
        return orderRepository.findAll()
                .stream()
                .map(order -> orderMapper.orderToOrderResponseDto(order))
                .toList();
    }

    @Override
    public InventoryResponseDto addOrder(OrderRequestDto orderRequestDto) {
        log.info("Saving new order ...");
        return orderMapper.orderToOrderResponseDto(orderRepository.save(orderMapper.orderRequestDtoToOrder(orderRequestDto)));
    }

    @Override
    public InventoryResponseDto updateOrder(String id, OrderRequestDto orderRequestDto) {
        log.info("Updating ...");
        return null;
        /*Order existingOrder = orderRepository.findById(id).isPresent() ?
                orderRepository.findById(id).get() : null;
        if (existingOrder == null) {
            return null;
        }
        else {
            existingOrder.setName(orderRequestDto.getName());
            existingOrder.setDescription(orderRequestDto.getDescription());
            existingOrder.setPrice(orderRequestDto.getPrice());
            return orderMapper.orderToOrderResponseDto(orderRepository.save(existingOrder));
        }*/
    }

    @Override
    public void deleteOrder(String id) {
        log.info("Deleting ...");
        orderRepository.deleteById(id);
    }
}
