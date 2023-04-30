package com.eyamattoussi.orderservice.service;

import com.eyamattoussi.orderservice.dto.InventoryResponseDto;
import com.eyamattoussi.orderservice.dto.OrderLineItemsDto;
import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.model.Order;
import com.eyamattoussi.orderservice.model.OrderLineItems;
import com.eyamattoussi.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired(required=true)
    private WebClient webClient;

    public Order placeOrder(OrderRequestDto orderRequest) throws IllegalAccessException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        /**
         * check if the item is exists in stock
         * using webClient to retrieve data from inventory microservice
         */
        InventoryResponseDto[] inventoryResponseDtoArray = webClient.get()
                .uri("http://localhost:9092/inventory/isInStock",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();   // for synchronous call

        boolean allProductsAreInStock = Arrays.stream(inventoryResponseDtoArray)
                .allMatch(InventoryResponseDto::isInStock);
        if (allProductsAreInStock){
            return orderRepository.save(order);
        }
        else {
            throw new IllegalAccessException("Products are not in stock, please try later...");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
/** url without params
 * Boolean isExist = webClient.get()
 *                 .uri("http://localhost:9092/inventory/isInStock")
 *                 .retrieve()
 *                 .bodyToMono(Boolean.class)
 *                 .block();   // for synchronous call
 */