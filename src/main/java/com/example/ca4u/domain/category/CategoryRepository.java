package com.example.ca4u.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM CATEGORY c " +
            "WHERE c.parent IS NULL And c.categoryType = 'B'")
    List<Category> findTopCategories();

    @Query("SELECT c FROM CATEGORY c " +
            "WHERE c.parent IS NULL And c.categoryType = 'C'")
    List<Category> findCategories();


    @Query("SELECT c FROM CATEGORY c " +
            "WHERE c.parent.id = :categoryId")
    List<Category> findByParentId(long categoryId);
}
