package com.microserivce.order.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.microserivce.order.dto.InventoryResponse;
import com.microserivce.order.dto.OrderLineItemsDto;
import com.microserivce.order.dto.OrderRequest;
import com.microserivce.order.model.Order;
import com.microserivce.order.model.OrderLineItems;
import com.microserivce.order.repository.OrderRepository;

@Service
@Transactional
// The @Transactional annotation ensures that all the operations within this class are wrapped in a database transaction. If any operation fails, all operations will be rolled back.
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());;

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItems(orderLineItems);

        // map(orderLineItems -> orderLineItems.getSkuCode()) is same map(orderLineItems::getSkuCode)
        List<String> skuCodes = order.getOrderLineItems().stream()
                            .map(OrderLineItems::getSkuCode)
                            .toList();

        // localhost:8087 can be replaces witht ehsrice name i.e. http://inventory/api/invenotry
        // Call inventory service and place order if product is in stock
        InventoryResponse[] inventoryReponseArray = webClient.get().uri("http://localhost:8088/api/inventory", 
        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();

        boolean allProductStock = Arrays.stream(inventoryReponseArray).allMatch(inventoryResponse -> inventoryResponse.getIsInStock());
        
        if (allProductStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock, please try after some time");
        }
        
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}
