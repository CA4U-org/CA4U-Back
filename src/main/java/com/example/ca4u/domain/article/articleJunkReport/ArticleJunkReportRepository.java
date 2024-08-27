package com.example.ca4u.domain.article.articleJunkReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleJunkReportRepository extends JpaRepository<ArticleJunkReport, Long> {
}
