package com.grocery.Controllers;

import com.grocery.Service.GroceryService;
import com.grocery.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionPro/grocery_items")
public class GroceryItemController {
    @Autowired
    private GroceryService groceryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryItem addGroceryItem(@RequestBody GroceryItem groceryItem) {
        return groceryService.addGroceryItem(groceryItem);
    }

    @GetMapping
    public List<GroceryItem> getAllGroceryItems() {
        return groceryService.getAllGroceryItems();
    }
    @GetMapping("/{id}")
    public GroceryItem getGroceryItemById(@PathVariable Long id) {
        return groceryService.getGroceryItem(id);
    }

    @PutMapping("/{id}")
    public GroceryItem updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) {
        return groceryService.updateGroceryItem(id, groceryItem);
    }

    @DeleteMapping("/{id}")
    public void deleteGroceryItem(@PathVariable Long id) {
        groceryService.deleteGroceryItem(id);
    }

    @PutMapping("/{id}/inventory")
    public GroceryItem updateInventory(@PathVariable Long id, @RequestParam Integer quantity) {
        return groceryService.updateInventory(id, quantity);
    }

}
