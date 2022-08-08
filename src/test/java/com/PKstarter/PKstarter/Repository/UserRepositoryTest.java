package com.PKstarter.PKstarter.Repository;


import com.PKstarter.PKstarter.domain.Role;
import com.PKstarter.PKstarter.domain.User;
import com.PKstarter.PKstarter.infrastructure.persistence.UserRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder encoder;

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저_생성_가져오기() {
        String username = "coco";
        String rawPassword = "123!@#qwe";
        //String encPassword = encoder.encode(rawPassword);
        userRepository.save(User.builder().username(username).password(rawPassword).
                nickname("홍길동").email("coco@nate.com").role(Role.USER).phoneNumber("01053787946").build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);

        Assertions.assertThat(user.getUsername()).isEqualTo(username);
        Assertions.assertThat(user.getPassword()).isEqualTo(rawPassword);
    }
}
