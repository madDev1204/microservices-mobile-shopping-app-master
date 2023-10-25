package com.example.service;

import com.example.dto.InventoryResponse;
import com.example.dto.OrderLineItemsDto;
import com.example.dto.OrderRequest;
import com.example.entity.Order;
import com.example.entity.OrderLineItems;
import com.example.event.OrderPlacedEvent;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    //topic, Object in send() method as key value pair
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order(); //order object has 3 attributes
        //order number
        order.setOrderNumber(UUID.randomUUID().toString());
        //mapping object of orderLineItemsDto to orderLineItems
        List<OrderLineItems> orderLineItems =
                orderRequest.getOrderLineItemsDtoList()
                        .stream()
                        .map(this::mapToDto)
                        .toList();

        order.setOrderLineItemsList(orderLineItems); //order created from order request

        //list of order skuCodes
        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        //before saving order to db CHECK INVENTORY - SYNC COMMUNICATION EXAMPLE
        //calling Inventory service
        //placing order only if product in stock - check if skuCode available

            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    //   .uri("http://localhost:8082/api/inventory",  //url should not be hardcoded! REPLACE without
                    .uri("http://inventory-service/api/inventory",
                            //sending list of order skuCodes to check in inventory
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class) //specifying return type of result
                    .block();  //blocking operation for synchronous communication

            //only if all items are in inventory accept the order i.e., if isInStock for ALL inventory items is true then only return true
            boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
            if (allProductsInStock) {
                //notification!!!!
                kafkaTemplate.send("notifyTopic",new OrderPlacedEvent(order.getOrderNumber()));
                //save order to db if result true
                orderRepository.save(order);
                return "Order placed successfully";
            } else {
                throw new IllegalArgumentException("One or more products not in stock, try again later");
            }

        }


    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }
}
