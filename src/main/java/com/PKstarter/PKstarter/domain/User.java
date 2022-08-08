package com.PKstarter.PKstarter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// @Data로 사용가능하지만 그중 Setter를 뺐다.
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( length = 30, unique = true,nullable = false)
    private String username; // 아이디

    @Column( unique = true,nullable = false)
    private String nickname;

    @Column(length = 100)
    private String password;

    @Column( length = 50, unique = true,nullable = false)
    private String email;

    @Column( length = 50, unique = true,nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* 회원정보 수정 */
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}