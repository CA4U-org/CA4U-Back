package com.example.ca4u.domain.category;

import com.example.ca4u.domain.guild.Guild;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //소속 카테고리 3가지 목록 조회
    public List<CategoryDto> getCategoryBelongs() {
        List<Category> categoryList = categoryRepository.findTopCategories();  //type을 B로
        return categoryList.stream().map(CategoryDto::of).toList();
    }

    //카테고리별로 보기 6가지 목록 조회
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = categoryRepository.findCategories();  //type을 C로
        return categoryList.stream().map(CategoryDto::of).toList();
    }

    //카테고리에 포함된 카테고리 목록 조회 (개선필요해보임)
    public List<CategoryDeptResponseDto> getCategoryById(long categoryId) {
        List<CategoryDeptResponseDto> categoryDeptResponseDtoList = new ArrayList<>();

        //처음에 인문대학, 경영경제대학 등등 조회
        List<Category> categoryList = categoryRepository.findByParentId(categoryId);

        //해당 대학에 속한 학과들 조회 (인문대학의 경우 -> 국어국문, 영어영문 등등)
        for (Category category : categoryList) {
            //DTO 생성
            CategoryDeptResponseDto categoryDeptResponseDto = new CategoryDeptResponseDto();

            //해당 대학에 속한 sub 카테고리들을 조회
            List<CategoryDto> subCategoryDtoList = categoryRepository.findByParentId(category.getId()).stream().map(CategoryDto::of).toList();

            //DTO 리스트에 값 넣기 (categoryDeptResponseDto 의 of메소드가 현재 정적메서드가 아님)
            categoryDeptResponseDtoList.add(categoryDeptResponseDto.of(category.getCategoryNm(), subCategoryDtoList, category.getImgUrl()));
        }

       return categoryDeptResponseDtoList;
    }

    //카테고리에 포함된 길드 목록들 조회
    public CategoryGuildResponseDto getCategoryGuilds(long categoryId) {
        //카테고리 불러오기
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
        //카테고리에 속한 길드리스트 불러오기
        List<Guild> guildList = category.getGuildList();
        //DTO로 변환하여 리턴
        return CategoryGuildResponseDto.of(category, guildList);
    }
}
