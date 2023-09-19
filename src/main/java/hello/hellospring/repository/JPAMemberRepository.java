package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JPAMemberRepository implements MemberRepository{
    private final EntityManager em; // 스프링부트가 알아서 em 을 만들어주는데 우리는 주입만 해주면된다.

    @Autowired
    public JPAMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 맴버 엔티티를 조회해 
        // 근데 jpa 는 엔티티 자체를 조회한다.
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }
}
