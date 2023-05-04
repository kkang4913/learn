package hello.servlet.domain.member;

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
