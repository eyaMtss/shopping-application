package com.eyamattoussi.inventoryservice.controller;

import com.eyamattoussi.inventoryservice.dto.InventoryResponseDto;
import com.eyamattoussi.inventoryservice.model.Inventory;
import com.eyamattoussi.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/isInStock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Inventory> getAll(){
        return  inventoryService.getAll();
    }
}
