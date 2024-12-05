package com.grocery.Service;

import com.grocery.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategory(Long id);
    Category addCategory(Category category);
    void deleteCategory(Long id);
}
