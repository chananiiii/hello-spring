package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.*;

@Configuration
public class SpringConfig {
    /*
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */


    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 아래 2개로 인해서 스프링올라올때 스프링컨테이너에 두개 다 올린다.
    // 그릭 다 연결이 된다.

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return  new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JPAMemberRepository(em);
        return
    }
    */
}
