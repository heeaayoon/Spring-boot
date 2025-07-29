package edu.pnu.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component //@Component : IOC 컨테이너에 클래스 이름으로 만든 객체(coffeeMachine)를 저장 by spring
public class CoffeeMaker {
	@Autowired //@Autowired : 컨테이너 내에 저장된 객체 중에서 CoffeeMachine 타입의 객체를 자동 연결 --> Spring 컨테이너에 등록된 객체 중에서, 필요한 타입의 객체를 자동으로 찾아서 여기에 주입하라
	private CoffeeMachine coffeeMachine;
	
//	public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
//		this.coffeeMachine = coffeeMachine;
//	}
	
	@PostConstruct
	public void makeCoffee() {
		System.out.println(coffeeMachine.brew());
	}
}
