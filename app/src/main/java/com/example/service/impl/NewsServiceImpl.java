package com.example.service.impl;

import com.example.exception.NotFoundException;
import com.example.filter.impl.NewsFilter;
import com.example.model.Category;
import com.example.model.News;
import com.example.model.User;
import com.example.repository.NewsRepository;
import com.example.service.CategoryService;
import com.example.service.CommentService;
import com.example.service.NewsService;
import com.example.service.UserService;
import com.example.util.EntityUpdater;
import com.example.util.SortingFilteringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.util.SortingFilteringUtils.byDateTimeRange;
import static com.example.util.SortingFilteringUtils.getPageableByFilter;
import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final EntityUpdater updater;

    @Transactional(readOnly = true)
    @Override
    public List<Pair<News, Integer>> findAll(NewsFilter filter) {
        return repository.findAll(
                        SortingFilteringUtils.<News, User>
                                        byJoinedField(filter.getAuthor(), "name", "user")
                                .and(SortingFilteringUtils.<News, Category>
                                        byJoinedField(filter.getCategory(), "title", "category"))
                                .and(byDateTimeRange(filter.getCreatedAfter(), filter.getCreatedBefore(), "createAt"))
                                .and(byDateTimeRange(filter.getUpdatedAfter(), filter.getUpdatedBefore(), "updateAt")),
                        getPageableByFilter(filter))
                .stream()
                .map(n -> Pair.of(n, commentService.countByNews(n.getId())))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public News findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Новость с id {0} не найдена", id)));
    }

    @Transactional
    @Override
    public News save(Long userId, Long categoryId, News news) {
        User user = userService.findById(userId);
        Category category = categoryService.findById(categoryId);
        news.setUser(user);
        news.setCategory(category);
        return repository.save(news);
    }

    @Transactional
    @Override
    public News updateById(Long newsId, News fromDto) {
        News fromDB = findById(newsId);
        updater.updateNews(fromDB, fromDto);
        return repository.save(fromDB);
    }

    @Transactional
    @Override
    public void deleteById(Long newsId) {
        repository.deleteById(newsId);
    }

    @Transactional(readOnly = true)
    @Override
    public void checkExistById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(format("Новость с id {0} не найдена", id));
        }
    }
}
