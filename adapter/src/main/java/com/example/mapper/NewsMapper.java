package com.example.mapper;

import com.example.dto.news.request.CreateNewsRequest;
import com.example.dto.news.request.UpdateNewsRequest;
import com.example.dto.news.response.FullNewsResponse;
import com.example.dto.news.response.ShortNewsResponse;
import com.example.dto.news.response.UpdateNewsResponse;
import com.example.model.News;
import com.example.utils.annotations.CommentMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.util.Pair;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CommentMapper.class)
public interface NewsMapper {

    News fromDto(CreateNewsRequest request);

    News fromDto(UpdateNewsRequest request);

    @Mapping(target = "author", source = "user.name")
    @Mapping(target = "category", source = "category.title")
    @Mapping(target = "comments", qualifiedBy = CommentMapping.class)
    FullNewsResponse toFullNewsResponse(News news);

    @Mapping(target = "author", source = "news.user.name")
    @Mapping(target = "category", source = "news.category.title")
    ShortNewsResponse toShortNewsResponse(News news, Integer commentsCount);

    @Mapping(target = "author", source = "user.name")
    @Mapping(target = "category", source = "category.title")
    UpdateNewsResponse toUpdateNewsResponse(News news);

    default List<ShortNewsResponse> toShortNewsResponseList(List<Pair<News, Integer>> newsWithCommentsCount) {
        return newsWithCommentsCount
                .stream()
                .map(p -> toShortNewsResponse(p.getFirst(), p.getSecond()))
                .toList();
    }

}
