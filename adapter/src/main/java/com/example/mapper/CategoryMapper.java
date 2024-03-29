package com.example.mapper;

import com.example.dto.category.request.UpdateCategoryDto;
import com.example.dto.category.response.FullCategoryInfoDto;
import com.example.dto.category.request.CreateCategoryDto;
import com.example.dto.category.response.ShortCategoryInfoDto;
import com.example.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = UserMapper.class)
public interface CategoryMapper {

    Category fromDto(CreateCategoryDto request);

    Category fromDto(UpdateCategoryDto request);

    @Mapping(target = "author", source = "user.name")
    FullCategoryInfoDto toCategoryFullResponse(Category category);

    @Mapping(target = "author", source = "user.name")
    ShortCategoryInfoDto toCategoryShortResponse(Category category);

    @Mapping(target = "author", source = "user.name")
    List<ShortCategoryInfoDto> toCategoryShortResponseList(Iterable<Category> categories);
}
