package edu.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("classpath:beans.xml") //IOC 컨테이너에 생성이 됨
public class TestIoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestIoCApplication.class, args);
	}

}
