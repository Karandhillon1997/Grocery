package com.grocery.Service;

import com.grocery.model.GroceryItem;

import java.util.List;

public interface GroceryService {
    List<GroceryItem> getAllGroceryItems();
    GroceryItem getGroceryItem(Long id);
    GroceryItem addGroceryItem(GroceryItem groceryItem);
    GroceryItem updateGroceryItem(Long id, GroceryItem groceryItem);
    void deleteGroceryItem(Long id);
    GroceryItem updateInventory(Long id, Integer quantity);
}
