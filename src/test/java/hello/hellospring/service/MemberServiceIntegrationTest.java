package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링부트 테스트 시 어노테이션 적는다
@SpringBootTest
@Transactional // 얘가 없으면, afterEach 만들고.. delete 만들고... 해야한다.
class MemberServiceIntegrationTest {

    // test 는 사실 제일 끝단에 있기 때문에
    // 주입할 때 편한 방법을 사용하면된다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        // 주어지고, 실행했을때, 이것이 나와야돼. given, when, then 이다.
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        }
        */

        // then
    }
}