package hello.hellospring.domain;

import javax.persistence.*;

// jpa 가 매핑하는 엔티티구나 라고 선언해주는 것이ㅏㄷ

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username") // db의 컬럼id 는 username 이다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
