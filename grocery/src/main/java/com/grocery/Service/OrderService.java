package com.grocery.Service;

import com.grocery.model.*;
import java.util.*;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrder(Long id);
    Order createOrder(List<Long> groceryItemIds, List<Integer> quantities);
    Order updateOrder(Long id, Order order);
    void cancelOrder(Long id);
}
