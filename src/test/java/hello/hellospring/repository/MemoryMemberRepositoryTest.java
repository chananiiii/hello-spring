package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// 테스트를 먼저 만들고, 구현체를 구현하는것
// 테스트에 맞게 개발하는 것
// 그것을 TDD(Test Driven development) 라고 한다. 테스트 주도 개발
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        // 콜백 메소드다, 세브가 끝나고 이거, findBy가 끝나고 여기
        // 테스트가 끝나고 실행된 후, 저장소가 다 지워진다.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring1");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // aasertThat 에서 null, result 비교 후, 테스트 케이스 통과하지않으면
        // 빨간색으로 로그 뜨고 다음 동작을하지 않는다.
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 이건 통과 된다.
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

        // 하지만 이건 통과되지 않는다.
        // result = repository.findByName("spring2").get();
        // assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // class 단위에서 실행하니, 오류난다.
        // 순서를 보장해주지 않는다.. 테스트시
        // 그래서 각 테스트가 끝나면 데이터를 clean 시켜줘야한다.
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}