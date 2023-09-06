 package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

 @SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		// 바로 시작하면 Whitelable Error Page 뜬다.
		// 아무것도 없기 때문에 에러가 떠야 맞는 것
		// 자체적인 톰캣이나 웹서버를 자체적으로 띄우면서 스프링부트가 같이 올라간다.

		// 처음에 시작하면 index.html 로 간다.
		// welcome 페이지
		SpringApplication.run(HelloSpringApplication.class, args);
	}
}
