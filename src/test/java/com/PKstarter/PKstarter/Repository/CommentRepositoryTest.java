package com.PKstarter.PKstarter.Repository;

import com.PKstarter.PKstarter.domain.Comment;
import com.PKstarter.PKstarter.domain.Posts;
import com.PKstarter.PKstarter.domain.Role;
import com.PKstarter.PKstarter.domain.User;
import com.PKstarter.PKstarter.infrastructure.persistence.CommentRepository;

import com.PKstarter.PKstarter.infrastructure.persistence.PostsRepository;
import com.PKstarter.PKstarter.infrastructure.persistence.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void clear() {
        commentRepository.deleteAll();
    }

    // 테스트 시에 같은 아이디를 가진 user를 두개 생성하여 넣을 시에 오류가 생긴 것이었다.
    // unique 속성을 가진 상태의 user 필드를 무시하고 동일한 두개 객체를 넣으니 오류가 생긴 것.
    @Test
    public void 게시글_댓글_생성_조회() {
        String content = "댓글 입니다.";
        // User save
        User user = User.builder().username("MJ").password("123!@#qwe").
                nickname("홍길동").email("coco@nate.com").role(Role.USER).phoneNumber("01053787946").build();
        userRepository.save(user);

        //Post save
        Posts posts = Posts.builder().title("제목 입니다.").content("내용 입니다").writer("MJ").build();
        postsRepository.save(posts);

        commentRepository.save(Comment.builder()
                .comment(content)
                .user(user)
                .posts(posts)
                .build());

        List<Comment> comments = commentRepository.getCommentByPostsOrderById(posts);

        Comment comment = comments.get(0);

        assertThat(comment.getComment()).isEqualTo(content);
    }

    @Test
    public void 랜덤_댓글_생성() {
        // User save
        User user = User.builder().username("M").password("13!@#qwe").
                nickname("홍길").email("co@nate.com").role(Role.USER).phoneNumber("010787946").build();
        userRepository.save(user);

        //Post save
        Posts posts = Posts.builder().title("제목 입니다.").content("내용 입니다").writer("MJ").build();
        postsRepository.save(posts);

        IntStream.rangeClosed(1, 20).forEach(i -> {
            long id = (long)(Math.random() * 22) + 1;

            Comment comment = Comment.builder()
                        .comment(i + "번째 댓글입니다.")
                        .user(user)
                        .posts(posts)
                        .build();

            commentRepository.save(comment);
        });
    }
}