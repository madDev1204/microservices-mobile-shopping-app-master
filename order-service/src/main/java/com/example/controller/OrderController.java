package com.example.controller;

import com.example.dto.OrderRequest;
import com.example.entity.Order;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@ResponseStatus(HttpStatus.CREATED)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }

    @GetMapping
    public List<Order> findAllOrders(){
        return orderService.findAllOrders();
    }

}
