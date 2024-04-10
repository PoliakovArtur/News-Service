package com.example.facade;

import com.example.dto.category.response.FullCategoryResponse;
import com.example.dto.category.response.ShortCategoryResponse;
import com.example.mapper.CategoryMapper;
import com.example.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.entity_examples.category.CategoryDbExamples.FIRST_PAGE;
import static com.example.entity_examples.category.CategoryDbExamples.SPORT_CATEGORY;
import static com.example.entity_examples.category.CategoryDbExamples.SPORT_CATEGORY_AFTER_MAPPING;
import static com.example.entity_examples.category.CategoryDbExamples.UPDATED_SPORT_CATEGORY;
import static com.example.entity_examples.category.CategoryDbExamples.UPDATE_SPORT_CATEGORY_AFTER_MAPPING;
import static com.example.entity_examples.category.CategoryRequestExamples.SIMPLE_CATEGORY_FILTER;
import static com.example.entity_examples.category.CategoryRequestExamples.VALID_CREATE_SPORT_CATEGORY_REQUEST;
import static com.example.entity_examples.category.CategoryRequestExamples.VALID_UPDATE_SPORT_CATEGORY_REQUEST;
import static com.example.entity_examples.category.CategoryResponseExamples.FULL_SPORT_CATEGORY_RESPONSE;
import static com.example.entity_examples.category.CategoryResponseExamples.SHORT_CATEGORY_RESPONSE_LIST;
import static com.example.entity_examples.category.CategoryResponseExamples.SHORT_SPORT_CATEGORY_RESPONSE;
import static com.example.entity_examples.category.CategoryResponseExamples.SHORT_UPDATED_SPORT_CATEGORY_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryFacadeTest {

    @InjectMocks
    private CategoryFacade facade;

    @Mock
    private CategoryService service;

    @Spy
    private CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void findAll() {
        when(service.findAll(SIMPLE_CATEGORY_FILTER)).thenReturn(FIRST_PAGE);
        List<ShortCategoryResponse> actual = facade.findAll(SIMPLE_CATEGORY_FILTER);

        assertEquals(SHORT_CATEGORY_RESPONSE_LIST, actual);
        verify(service, times(1)).findAll(SIMPLE_CATEGORY_FILTER);
    }

    @Test
    void findById() {
        when(service.findById(1L)).thenReturn(SPORT_CATEGORY);
        FullCategoryResponse actual = facade.findById(1L);

        assertEquals(FULL_SPORT_CATEGORY_RESPONSE, actual);
        verify(service, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(service.save(1L, SPORT_CATEGORY_AFTER_MAPPING)).thenReturn(SPORT_CATEGORY);
        ShortCategoryResponse actual = facade.save(VALID_CREATE_SPORT_CATEGORY_REQUEST);

        assertEquals(SHORT_SPORT_CATEGORY_RESPONSE, actual);
        verify(service, times(1)).save(1L, SPORT_CATEGORY_AFTER_MAPPING);
    }

    @Test
    void update() {
        when(service.updateById(1L, UPDATE_SPORT_CATEGORY_AFTER_MAPPING)).thenReturn(UPDATED_SPORT_CATEGORY);
        ShortCategoryResponse actual = facade.updateById(1L, VALID_UPDATE_SPORT_CATEGORY_REQUEST);

        assertEquals(SHORT_UPDATED_SPORT_CATEGORY_RESPONSE, actual);
        verify(service, times(1)).updateById(1L, UPDATE_SPORT_CATEGORY_AFTER_MAPPING);
    }
}
