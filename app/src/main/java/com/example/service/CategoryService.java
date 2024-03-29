package com.example.service;

import com.example.filter.impl.CategoryFilter;
import com.example.model.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Page<Category> findAll(CategoryFilter filter);

    Category findById(Long id);

    Category save(Long userId, Category category);

    Category updateById(Long categoryId, Category fromDto);

    void deleteById(Long categoryId);

}
