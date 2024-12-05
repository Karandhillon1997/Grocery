package com.grocery.Service;

import com.grocery.Repository.GroceryItemRepository;
import com.grocery.exception.GroceryItemNotFoundException;
import com.grocery.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroceryServiceImpl implements GroceryService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    @Override
    public GroceryItem getGroceryItem(Long id) {
        return groceryItemRepository.findById(id)
                .orElseThrow(() -> new GroceryItemNotFoundException("Grocery item with ID " + id + " not found"));
    }

    @Override
    public GroceryItem addGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public GroceryItem updateGroceryItem(Long id, GroceryItem groceryItem) {
        if (!groceryItemRepository.existsById(id)) {
            throw new GroceryItemNotFoundException("Grocery item with ID " + id + " not found for update");
        }
        groceryItem.setId(id);
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public void deleteGroceryItem(Long id) {
        if (!groceryItemRepository.existsById(id)) {
            throw new GroceryItemNotFoundException("Grocery item with ID " + id + " not found for deletion");
        }
        groceryItemRepository.deleteById(id);
    }

    @Override
    public GroceryItem updateInventory(Long id, Integer quantity) {
        GroceryItem groceryItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new GroceryItemNotFoundException("Grocery item with ID " + id + " not found"));

        groceryItem.setQuantity(groceryItem.getQuantity() + quantity);
        return groceryItemRepository.save(groceryItem);
    }
}
