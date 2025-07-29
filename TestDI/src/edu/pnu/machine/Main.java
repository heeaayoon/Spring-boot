package edu.pnu.machine;

public class Main {
	public static void main(String[] args) {
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		coffeeMaker.setCoffeeMachine(new EspressoMachine());
		coffeeMaker.makeCoffee();
		
		CoffeeMaker coffeeMaker2 = new CoffeeMaker();
		coffeeMaker2.setCoffeeMachine(new DripCoffeeMachine()); // 인터페이스를 이용하면 이 부분만 수정하면 됨
		coffeeMaker2.makeCoffee();
	}
}
