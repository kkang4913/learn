# `서블릿 JSP MVC패턴`

- ### 회원 관리 웹 애플리케이션 요구사항
  - <b>회원 정보</b>
    - 이름: `username`
    - 나이: `age`

   - <b>기능 요구사항</b>
     - 회원 저장  
     - 회원 목록 조회

<b>회원 도메인 모델</b>
```java 
package hello.servlet.domain.member;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
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
```

<b>회원 저장소</b>
```java
package hello.learn.domain.member;

import java.util.*;

public class MemberRepository {
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */
    private static Map<Long,Member> store= new HashMap<>();
    private static long sequence = 0L;

    private  static  final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    public MemberRepository() {
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public  void clearStore(){
        store.clear();
    }
    /*
    회원 저장소는 싱글톤 패턴을 적용했다. 스프링을 사용하면 스프링 빈으로 등록하면 되지만, 지금은 최대한
    스프링 없이 순수 서블릿 만으로 구현하는 것이 목적이다.
    싱글톤 패턴은 객체를 단 하나만 생생해서 공유해야 하므로 생성자를 private 접근자로 막아둔다.
     */
}
```
- 참고
  - 회원 저장소는 싱글톤 패턴을 적용했다. 스프링을 사용하면 스프링 빈으로 등록하면 되지만, 지금은 최대한 스프링 없이 순수 서블릿 만으로 구현하는 것이 목적이다. 싱글톤 패턴은 객체를 단 하나만 생생해서 공유해야 하므로 생성자를 private 접근자로 막아둔다.   
  
<b>회원 저장소 테스트 코드 방법</b>
/ 단축키: `ctrl + Shift + T` </p>
```java
package hello.learn.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    //테스트 메소드를 실행 한 후 저장소를 초기화 하여 테스트 간 영향을 없애준다.
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given *** 이 주어졌을때
        Member member = new Member("hello",20);

        //when *** 을 실행 했을 때
        Member savedMember = memberRepository.save(member);

        //then 결과가 이거여야해
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
        // 찾아온 멤버는 저장된 멤버와 같아야 한다.
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        //assertThat : 검증하고자 하는 값과 기대하는 값을 비교하여 일치하는지 알려준다.
        assertThat(result.size()).isEqualTo(2); //isEqualTo : 기대하는 값
        assertThat(result).contains(member1,member2); // contains: 포함되어야하는 요소1,2,3 등등
        // 포함되어야 하는 요소가 하나라도 빠지거나 추가된 경우 예외가 발행한다.

    }
}

```