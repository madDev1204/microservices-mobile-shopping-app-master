package com.example.service;

import com.example.dto.InventoryResponse;
import com.example.entity.Inventory;
import com.example.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
//    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        //code breaks with this COMMENT IT
//        log.info("Wait started");
//      //simulating slow behaviour in network
//        Thread.sleep(10000);
//        log.info("Wait ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }

    public List<Inventory> getAll(){
        return inventoryRepository.findAll();
    }
}
