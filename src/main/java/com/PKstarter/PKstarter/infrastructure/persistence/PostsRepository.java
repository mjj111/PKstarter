package com.PKstarter.PKstarter.infrastructure.persistence;

import com.PKstarter.PKstarter.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);

    // 제목 검색
    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);
}