package com.example.entity_examples.category;

import com.example.dto.category.request.CreateCategoryRequest;
import com.example.dto.category.request.UpdateCategoryRequest;
import com.example.filter.impl.CategoryFilter;

public class CategoryRequestExamples {

    public final static CreateCategoryRequest VALID_CREATE_CATEGORY_REQUEST = CreateCategoryRequest.builder()
            .title("Sport")
            .description("News about Sport")
            .userId(1L)
            .build();

    public final static CreateCategoryRequest INVALID_CREATE_CATEGORY_REQUEST = CreateCategoryRequest.builder()
            .title("    ")
            .description("News about Sport")
            .userId(1L)
            .build();

    public final static UpdateCategoryRequest VALID_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest.builder()
            .userId(1L)
            .description("News about sport and athletes")
            .build();

    public final static CategoryFilter SIMPLE_CATEGORY_FILTER = CategoryFilter.builder()
            .pageNumber(0)
            .pageSize(1)
            .desc(false)
            .build();
}
