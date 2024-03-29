package com.example.dto.news.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateNewsInfoDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String category;
}
