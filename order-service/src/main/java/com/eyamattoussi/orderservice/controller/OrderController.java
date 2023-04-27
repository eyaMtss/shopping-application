package com.eyamattoussi.orderservice.controller;

import com.eyamattoussi.orderservice.dto.OrderRequestDto;
import com.eyamattoussi.orderservice.dto.InventoryResponseDto;
import com.eyamattoussi.orderservice.exception.OrderNotFoundException;
import com.eyamattoussi.orderservice.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping(path = "/add")
    public ResponseEntity<InventoryResponseDto> addOrder(@Valid @RequestBody OrderRequestDto orderRequestDTO) {
        return new ResponseEntity<>(orderService.addOrder(orderRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<InventoryResponseDto> updateOrder(@PathVariable String id, @Valid @RequestBody OrderRequestDto orderRequestDTO){
        try {
            return new ResponseEntity<>(orderService.updateOrder(id, orderRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new OrderNotFoundException("Order not found");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InventoryResponseDto>> getOrders() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<InventoryResponseDto> getOrdersById(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new OrderNotFoundException("Order doesn't exist");
        }
    }
}


