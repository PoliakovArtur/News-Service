package com.example.facade;

import com.example.dto.category.request.CreateCategoryRequest;
import com.example.dto.category.request.UpdateCategoryRequest;
import com.example.dto.category.response.FullCategoryResponse;
import com.example.dto.category.response.ShortCategoryResponse;
import com.example.filter.impl.CategoryFilter;
import com.example.mapper.CategoryMapper;
import com.example.service.CategoryService;
import com.example.utils.annotations.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService service;
    private final CategoryMapper mapper;

    public List<ShortCategoryResponse> findAll(CategoryFilter filter) {
        return mapper.toCategoryShortResponseList(service.findAll(filter));
    }

    public FullCategoryResponse findById(Long id) {
        return mapper.toCategoryFullResponse(service.findById(id));
    }

    public ShortCategoryResponse save(CreateCategoryRequest request) {
        return mapper.toCategoryShortResponse(service.save(request.getUserId(), mapper.fromDto(request)));
    }

    public ShortCategoryResponse updateById(Long categoryId, UpdateCategoryRequest request) {
        return mapper.toCategoryShortResponse(service.updateById(categoryId, mapper.fromDto(request)));
    }

    public void deleteById(Long userId, Long categoryId) {
        service.deleteById(categoryId);
    }
}
