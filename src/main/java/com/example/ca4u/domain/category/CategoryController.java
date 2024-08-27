package com.example.ca4u.domain.category;

import com.example.ca4u.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "카테고리", description = "카테고리 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "소속별로 보기", description = "소속별로 보기에 있는 3가지 카테고리 목록을 조회합니다.")
    @GetMapping("/belongs")
    public ApiResponse<List<CategoryDto>> getBelongs() {
        return ApiResponse.ok(categoryService.getCategoryBelongs(), "소속별로 보기 목록 조회 성공");
    }

    @Operation(summary = "카테고리별로 보기", description = "카테고리별로 보기에 있는 6가지 카테고리 목록을 조회합니다.")
    @GetMapping("/categories")
    public ApiResponse<List<CategoryDto>> getCategories() {
        return ApiResponse.ok(categoryService.getCategories(), "카테고리별로 보기 목록 조회 성공");
    }
    
    //카테고리의 카테고리 보기
    @Operation(summary = "카테고리의 포함된 길드 불러오기", description = "카테고리에 포함되어있는 동아리 or 학회 목록을 조회합니다.")
    @GetMapping("/categories/{categoryId}/guilds")
    public ApiResponse<CategoryGuildResponseDto> getCategoryGuilds(@PathVariable long categoryId) {
        return ApiResponse.ok(categoryService.getCategoryGuilds(categoryId), "카테고리에 포함된 길드 목록 조회 성공");
    }

    @Operation(summary = "카테고리의 카테고리들 불러오기", description = "카테고리에 포함되어있는 카테고리 목록을 조회합니다.(단과대 별로 보기)")
    @GetMapping("/categories/{categoryId}")
    public ApiResponse<List<CategoryDeptResponseDto>> getCategoryCategories(@PathVariable long categoryId) {
        return ApiResponse.ok(categoryService.getCategoryById(categoryId), "카테고리에 포함된 카테고리 목록 조회 성공");
    }
}
