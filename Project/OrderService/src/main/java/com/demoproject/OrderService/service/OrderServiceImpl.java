package com.demoproject.OrderService.service;

import com.demoproject.OrderService.entity.Order;
import com.demoproject.OrderService.external.client.ProductService;
import com.demoproject.OrderService.model.OrderRequest;
import com.demoproject.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing order Request: ", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Creating ORder with Status Created");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .build();

        order = orderRepository.save(order);
        log.info("Order placed succesfully with order_id: ", order.getId());
        return order.getId();
    }
}
