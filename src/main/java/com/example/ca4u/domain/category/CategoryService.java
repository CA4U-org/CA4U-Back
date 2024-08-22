package com.example.ca4u.domain.category;

import com.example.ca4u.domain.guild.Guild;
import com.example.ca4u.domain.hashtag.Hashtag;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
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

    //카테고리에 포함된 길드 목록들 조회
/*    public List<CategoryGuildResponseDto> getCategoryGuilds(long categoryId) {

    }*/
}
