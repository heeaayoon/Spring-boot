package edu.pnu.machine;

import org.springframework.stereotype.Component;

@Component //@Component : IOC 컨테이너에 클래스 이름으로 만든 객체(espressoMachine)를 저장 by spring
public class EspressoMachine implements CoffeeMachine{
	@Override
	public String brew() {
		return "Espresso 머신에서 커피를 추출합니다";
	}
}
