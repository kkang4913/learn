package hello.learn.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
// 회원 도메인 모델
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member() {

    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
