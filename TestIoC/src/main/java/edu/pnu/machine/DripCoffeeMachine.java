package edu.pnu.machine;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component //Field coffeeMachine in edu.pnu.machine.CoffeeMaker required a single bean, but 2 were found -> CoffeeMachine 타입의 객체가 여러개인 경우, 뭘 연결할지 알 수 없음 -> @Primary를 붙여서 우선순위 만들기
public class DripCoffeeMachine implements CoffeeMachine {
	@Override
	public String brew() {
		return "drip 커피 머신에서 커피를 추출합니다";
	}
}
