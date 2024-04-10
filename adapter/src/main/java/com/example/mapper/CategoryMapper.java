package com.example.mapper;

import com.example.dto.category.request.CreateCategoryRequest;
import com.example.dto.category.request.UpdateCategoryRequest;
import com.example.dto.category.response.FullCategoryResponse;
import com.example.dto.category.response.ShortCategoryResponse;
import com.example.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = UserMapper.class)
public interface CategoryMapper {

    Category fromDto(CreateCategoryRequest request);

    Category fromDto(UpdateCategoryRequest request);

    @Mapping(target = "author", source = "user.name")
    FullCategoryResponse toCategoryFullResponse(Category category);

    @Mapping(target = "author", source = "user.name")
    ShortCategoryResponse toCategoryShortResponse(Category category);

    @Mapping(target = "author", source = "user.name")
    List<ShortCategoryResponse> toCategoryShortResponseList(Iterable<Category> categories);
}
