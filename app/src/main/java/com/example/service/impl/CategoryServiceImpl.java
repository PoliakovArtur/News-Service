package com.example.service.impl;

import com.example.exception.NotFoundException;
import com.example.filter.impl.CategoryFilter;
import com.example.model.Category;
import com.example.model.User;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import com.example.service.UserService;
import com.example.util.EntityUpdater;
import com.example.util.SortingFilteringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.util.SortingFilteringUtils.byDateTimeRange;
import static com.example.util.SortingFilteringUtils.getPageableByFilter;
import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final UserService userService;
    private final EntityUpdater updater;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Category> findAll(CategoryFilter filter) {
        return repository.findAll(
                SortingFilteringUtils.<Category, User>
                                byJoinedField(filter.getAuthor(), "name", "user")
                        .and(byDateTimeRange(filter.getCreatedAfter(), filter.getCreatedBefore(), "createAt"))
                        .and(byDateTimeRange(filter.getUpdatedAfter(), filter.getUpdatedBefore(), "updateAt")),
                getPageableByFilter(filter));
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Категория с id {0} не найдена", id)));
    }

    @Transactional
    @Override
    public Category save(Long userId, Category category) {
        User user = userService.findById(userId);
        category.setUser(user);
        return repository.save(category);
    }

    @Transactional
    @Override
    public Category updateById(Long categoryId, Category fromDto) {
        Category category = findById(categoryId);
        updater.updateNewsCategory(category, fromDto);
        return repository.save(category);
    }

    @Transactional
    @Override
    public void deleteById(Long categoryId) {
        repository.deleteById(categoryId);
    }

}
