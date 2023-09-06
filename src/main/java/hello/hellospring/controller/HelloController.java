package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        // model 에 data 값을 넣은 상태로 hello.html 을 실행해라 라는 의미이다.
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // required 가 기본적으로 true 기 때문에 파라미터를 무조건 넘겨야함, 안넘기면 에러다.
    // 대신 false 로 바뀌면 실행은 되지만, 타임리프에서 받아서 view 그려주는 곳에 데이터는 null 로 보인다.
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // ResponseBody 의 의미는, http 에서 헤더부와 바디부가 있는데, 바디 부에 이 데이터를 직접 넣어주겠다. 라는 것이다.
    // 데이터를 그대로 뿌려준다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // hello spring
    }

    // 이게 json 방식이다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName("PCW");

        // 객체를 반환해서 json 으로 반환하는게 디폴트로 설정되어있다.
        // 기본은 json 이다.
        // 2020년 시점에서는 json 방식으로 쓰는 것이 맞다.
        // 레거시에서는 xml 연동 방식도있는데.... 지금은 json 이 맞다.
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
