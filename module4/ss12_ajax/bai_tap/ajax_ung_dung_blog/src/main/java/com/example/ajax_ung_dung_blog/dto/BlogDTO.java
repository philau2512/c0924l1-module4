package com.example.ajax_ung_dung_blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Long id;
    private String title;
    private String excerpt;
    private String content;
    private String author;
    private Long categoryId;
}
