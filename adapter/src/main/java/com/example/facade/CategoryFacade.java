package com.example.facade;

import com.example.dto.category.request.CreateCategoryDto;
import com.example.dto.category.request.UpdateCategoryDto;
import com.example.dto.category.response.FullCategoryInfoDto;
import com.example.dto.category.response.ShortCategoryInfoDto;
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

    public List<ShortCategoryInfoDto> findAll(CategoryFilter filter) {
        return mapper.toCategoryShortResponseList(service.findAll(filter));
    }

    public FullCategoryInfoDto findById(Long id) {
        return mapper.toCategoryFullResponse(service.findById(id));
    }

    public ShortCategoryInfoDto save(CreateCategoryDto request) {
        return mapper.toCategoryShortResponse(service.save(request.getUserId(), mapper.fromDto(request)));
    }

    public ShortCategoryInfoDto updateById(Long categoryId, UpdateCategoryDto request) {
        return mapper.toCategoryShortResponse(service.updateById(categoryId, mapper.fromDto(request)));
    }

    public void deleteById(Long userId, Long categoryId) {
        service.deleteById(categoryId);
    }
}
