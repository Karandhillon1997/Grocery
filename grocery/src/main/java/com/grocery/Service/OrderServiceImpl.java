package com.grocery.Service;

import com.grocery.Repository.GroceryItemRepository;
import com.grocery.Repository.OrderRepository;
import com.grocery.model.GroceryItem;
import com.grocery.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import com.grocery.exception.OrderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
    }

    @Override
    public Order createOrder(List<Long> groceryItemIds, List<Integer> quantities) {
        if (groceryItemIds.size() != quantities.size()) {
            throw new IllegalArgumentException("Grocery item IDs and quantities must have the same size.");
        }

        // Fetch the grocery items by IDs
        List<GroceryItem> groceryItems = groceryItemIds.stream()
                .map(id -> groceryItemRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Grocery item with ID " + id + " not found")))
                .collect(Collectors.toList());

        Order order = new Order();
        double totalAmount = 0;

        for (int i = 0; i < groceryItems.size(); i++) {
            GroceryItem groceryItem = groceryItems.get(i);
            int quantity = quantities.get(i);
            totalAmount += groceryItem.getPrice() * quantity;
        }

        order.setGroceryItems(groceryItems);
        order.setTotalPrice(totalAmount);  // Set the calculated total price

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found for update");
        }
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found for cancellation");
        }
        orderRepository.deleteById(id);
    }
}
