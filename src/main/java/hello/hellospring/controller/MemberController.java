package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 자바 코드로 빈 등록하든, 컴포넌트 스캔을 이용하든, Controller는 사용해야한다.
@Controller
public class MemberController {
    // @Controller 가 있으면, 스프링컨트롤러가 주입하게 해주도록해야한다.
    // 스프링 컨트롤러에 등록하고 사용해야한다.

    // 이 방법이 set 주입 이다., 단점은 public 으로 열려야된다.
    // 근데 한번 설정하면, 바꿀일이 없는데 , public 으로 접근 가능성이 있다.
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    // 필드 주입, 이건 별로 안좋다.
    /*
    @Autowired
    private MemberService memberService;
     */

    // 생성자에 뜰때 주입을해준다.
    // 스프링 컨테이너에 있는 맴버 서비스에 연결을 해준다. Autowired를 사용하면.
    /*
    // 생성자 주입 방법
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
     */

    @GetMapping("/members/new")
    public String createForm () {
        return "members/createMemberForm";
    }

    // GetMapping 은 Url 들어올때 사용하고
    // PostMapping 은 form 에서 post 로 설정하는 것으로 들어올때 사용
    @PostMapping("members/new")
    public String create(MemberForm form) {
        // 여기서 MemberForm form 은 MemberForm 의 맴버 변수인 name과
        // html 상의 form 의 name 값과 매칭이 되어서 들어온다.
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
