package com.PKstarter.PKstarter.Service;

import com.PKstarter.PKstarter.application.PostsService;
import com.PKstarter.PKstarter.application.dto.PostsDto;
import com.PKstarter.PKstarter.domain.Role;
import com.PKstarter.PKstarter.domain.User;
import com.PKstarter.PKstarter.infrastructure.persistence.PostsRepository;
import com.PKstarter.PKstarter.infrastructure.persistence.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_생성() {
        User user = User.builder().username("김명준").nickname("MJ").email("skatks1016@naver.com").role(Role.USER).build();

        PostsDto.Request posts = PostsDto.Request.builder()
                .title("Test Title")
                .writer(user.getNickname())
                .content("Test Content")
                .view(0)
                .user(user)
                .build();

        postsService.save(posts, user.getNickname());

    }
}