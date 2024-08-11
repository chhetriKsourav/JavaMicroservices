package com.demoproject.OrderService.service;

import com.demoproject.OrderService.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
