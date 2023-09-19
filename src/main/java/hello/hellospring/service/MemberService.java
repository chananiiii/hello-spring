package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// ctrl + shfit + T 하면 테스트 케이스를 만들 수 있다. 편하게
// @Service를 선언하게 되면, 스프링이 올라올때, 얘를 서비스네 하고, 컨트롤러에 멤버서비스를 등록하게 된다.


@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 어 너도 맴버 레파지토리가 필요하네, 스프링에 있는 멤버레파지토리를 넣어줄께.. 라는 의미
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // 예전에는 null 체크할 때, 체크해주었지만
        // 지금은 optional 로 한번 감싸고, 간다.
        // 아래 반환형은 Optional 이다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        System.out.println("come here");
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    /*
     * 전체 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
