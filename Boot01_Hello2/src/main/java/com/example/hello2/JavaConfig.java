package com.example.hello2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello2.pc.Computer;
import com.example.hello2.pc.Cpu;
import com.example.hello2.util.Remocon;
import com.example.hello2.util.RemoconImpl;
import com.example.hello2.util.TvRemocon;

/*
 * 어떤 객체를 spring이 생성해서 관리할지 설정(bean 설정)
 * 
 * xml 문서로 설정하던 bean 설정을 class 기반으로 한다.
 */
@Configuration
public class JavaConfig {
	/*
	 * 이 메소드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록 한다.
	 * 
	 * 아래의 메소드는 xml 문서에서 <bean id="myCar" class="com.example.demo.Car" />와 같다.
	 */
	@Bean
	public Car myCar() { //method의 이름이 bean의 이름(id) 역할을 한다.
		System.out.println("myCar() 메소드 호출됨");
		Car c1 = new Car();
		return c1;
	};
	
	//Remocon 인터페이스 type이 spring이 관리하는 bean이 되도록 설정해보세요.
	
	@Bean
		//인터페이스 타입 사용 -> 의존관계를 느슨하게 하기 위해서
	public Remocon myRemocon() { //메소드 명은 내 맘대로.(빈의 이름) //bean 이름: myRemocon
		System.out.println("myRemocon() 메소드 호출함");
		Remocon r1 = new RemoconImpl();
		return r1;
		};
		
	@Bean
	public Remocon tvRemocon() { //bean 이름: tvRemocon
		Remocon r1 = new TvRemocon();
		return r1;
	}
	
	/*
	 * 스프링 입장에서
	 * Remocon type이 2개 
	 * 근데 이름은 다름 myRemocon / TvRemocon
	 * 
	 * 즉 같은 타입이 여러개 있을 경우 이름을 ㅘㅈ아가야함!! 
	 */
	
	@Bean
	public Cpu getCpu() {
		return new Cpu();
	}
	
	@Bean
	public Computer myComputer() {
		//생성자에 또 다른 Bean의 참조값을 필요하면 메소드를 호출해서 얻어내면 된다.
		Computer c1 = new Computer(getCpu());
		return c1;
		
	}
}

