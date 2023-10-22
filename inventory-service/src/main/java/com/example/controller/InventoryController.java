package com.example.controller;

import com.example.dto.InventoryResponse;
import com.example.entity.Inventory;
import com.example.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    //sending multiple skew codes as list in 1 http request
    // we cannot send one http request per sku code - it overloads the server unnecessarily

    //as path variable
    //http://localhost:8082/api/inventory/iphone-13,iphone12-red

    //as request parameter - this is used here
    //http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("/all")
    public List<Inventory> gettinAll(){
        return inventoryService.getAll();
    }
}
