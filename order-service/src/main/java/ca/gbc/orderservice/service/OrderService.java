package ca.gbc.orderservice.service;

import ca.gbc.orderservice.dto.OrderRequest;

public interface OrderService {
    /**
     * Place an order.
     *
     * @param orderRequest The request object containing details for the order.
     */
    void placeOrder(OrderRequest orderRequest);
}
