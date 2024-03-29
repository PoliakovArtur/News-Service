package com.example.mapper;

import com.example.dto.news.request.UpdateNewsDto;
import com.example.dto.news.response.FullNewsInfoDto;
import com.example.dto.news.request.CreateNewsDto;
import com.example.dto.news.response.ShortNewsInfoDto;
import com.example.dto.news.response.UpdateNewsInfoDto;
import com.example.model.News;
import com.example.utils.annotations.CommentMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CommentMapper.class)
public interface NewsMapper {

    News fromDto(CreateNewsDto request);

    News fromDto(UpdateNewsDto request);

    @Mapping(target = "author", source = "user.name")
    @Mapping(target = "category", source = "category.title")
    @Mapping(target = "comments", qualifiedBy = CommentMapping.class)
    FullNewsInfoDto toFullNewsResponse(News news);

    @Mapping(target = "author", source = "news.user.name")
    @Mapping(target = "category", source = "news.category.title")
    ShortNewsInfoDto toShortNewsResponse(News news, Integer commentsCount);

    @Mapping(target = "author", source = "user.name")
    @Mapping(target = "category", source = "category.title")
    UpdateNewsInfoDto toUpdateNewsResponse(News news);

    default List<ShortNewsInfoDto> toShortNewsResponseList(Map<News, Integer> newsWithCommentsCount) {
        return newsWithCommentsCount
                .entrySet()
                .stream()
                .map(e -> toShortNewsResponse(e.getKey(), e.getValue()))
                .toList();
    }

}
