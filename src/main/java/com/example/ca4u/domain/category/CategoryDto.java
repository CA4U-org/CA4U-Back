package com.example.ca4u.domain.category;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String categoryNm;
    private String categoryDesc;
    private String imgUrl;

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryNm(category.getCategoryNm())
                .categoryDesc(category.getCategoryDesc())
                .imgUrl(category.getImgUrl())
                .build();
    }
}
