package com.PKstarter.PKstarter.Repository;

import com.PKstarter.PKstarter.domain.Posts;
import com.PKstarter.PKstarter.infrastructure.persistence.PostsRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void reset() {
        postsRepository.deleteAll();
    }

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_생성_가져오기() {
        String title = "제목 입니다.";
        String content = "내용 입니다";

        postsRepository.save(Posts.builder().title(title).content(content).writer("MJ").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}