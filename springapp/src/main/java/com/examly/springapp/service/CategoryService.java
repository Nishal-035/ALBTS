package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Category;

@Service
public class CategoryService {

    private List<Category> categories = new ArrayList<>();

    public Category addCategory(Category category) {
        category.setCategoryId(1L);
        categories.add(category);
        return category;
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public Category getCategoryById(Long id) {
        return categories.get(0);
    }
}
