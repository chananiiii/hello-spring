package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// spring data jpa 가 구현체를 만들어서 등록해준다. 우리는 가져다가 쓰면된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {
    // select m from Member m where m.name = ?
    // JPQL 로 이렇게 된다 override 해주면..
    @Override
    Optional<Member> findByName(String name);
}
