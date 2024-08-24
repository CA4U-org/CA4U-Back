package com.example.ca4u.domain.category;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDeptResponseDto { //단과대별 카테고리 조회 응답 DTO
    private String deptNm; //단과대 이름
    private List<CategoryDto> categoryList; //단과대에 속한 카테고리 리스트
    private String imgUrl; //단과대 이미지 주소 (단과대 프로필 이미지) - 현재는 CAU 로고로 통일

    public CategoryDeptResponseDto of(String deptNm, List<CategoryDto> categoryList, String imgUrl){
        return CategoryDeptResponseDto.builder()
                .deptNm(deptNm)
                .categoryList(categoryList)
                .imgUrl(imgUrl)
                .build();
    }
}
