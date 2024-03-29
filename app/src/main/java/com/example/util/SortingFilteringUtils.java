package com.example.util;

import com.example.filter.Filter;
import jakarta.persistence.criteria.Join;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class SortingFilteringUtils {

    private SortingFilteringUtils() {}

    public static <Entity> Specification<Entity> byDateTimeRange(LocalDateTime after, LocalDateTime before, String fieldName) {
        return ((root, query, criteriaBuilder) -> {
            if(after == null && before == null) return null;
            if(after == null) return criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), before);
            if(before == null) return criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), after);
            return criteriaBuilder.between(root.get(fieldName), after, before);
        });
    }

    public static <Entity, Joined> Specification<Entity> byJoinedField(Object field, String fieldName, String joinField) {
        return (root, query, criteriaBuilder) -> {
            if(field == null) return null;
            Join<Entity, Joined> newsJoin = root.join(joinField);
            return criteriaBuilder.equal(newsJoin.get(fieldName), field);
        };
    }

    public static Pageable getPageableByFilter(Filter filter) {
        String sortParam = filter.getSort();
        if(sortParam == null)  {
            return PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        }
        Sort sort = Sort.by(sortParam);
        if(filter.getDesc()) sort = sort.descending();
        return PageRequest.of(
                filter.getPageNumber(),
                filter.getPageSize(),
                sort);
    }
}

