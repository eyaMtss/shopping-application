package com.eyamattoussi.orderservice.controller;

import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.model.Order;
import com.eyamattoussi.orderservice.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order savedOrder = orderService.placeOrder(orderRequestDto);
        if (savedOrder == null) {
            return "ERROR";
        }
        else {
            return "Order saved ..";
        }
    }
}


