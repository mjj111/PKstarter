package com.PKstarter.PKstarter.infrastructure.persistence;

import com.PKstarter.PKstarter.domain.Comment;
import com.PKstarter.PKstarter.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    /* 게시글 댓글 목록 가져오기  */
    List<Comment> getCommentByPostsOrderById(Posts posts);
}