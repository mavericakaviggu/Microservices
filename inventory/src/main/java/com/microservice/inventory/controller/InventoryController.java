package com.microservice.inventory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory.dto.InventoryResponse;
import com.microservice.inventory.service.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // @GetMapping("/{sku-code}")
    // @ResponseStatus(HttpStatus.OK)
    // public Boolean isInStock(@PathVariable("sku-code") String skuCode) {
    //         return inventoryService.isInStock(skuCode);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
            return inventoryService.isInStock(skuCode);
    }
    
}
