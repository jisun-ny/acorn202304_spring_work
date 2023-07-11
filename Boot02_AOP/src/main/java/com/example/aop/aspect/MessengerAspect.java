package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;

import com.example.aop.util.Messenger;

@Aspect
public class MessengerAspect {
	/*
	 * 이 친구를 bean을 만드는 방법은 
	 * 1.@component를 붙이거나
	 * 2.javacomponent에서 만들 수 있다.
	*/
	
	@Around("execution(void send*(..))")
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드에 전달된 인자들 목록을 얻어내기
		Object[] args = joinPoint.getArgs();
		
		//반복문 돌면서 타입 체크하면서 찾아내기
		for(Object tmp:args) {
			//만일 String type이면
			if(tmp instanceof String) {
				//원래 type으로 casting
				String msg = (String)tmp;
				System.out.println("aspect에서 읽어낸 내용:" + msg);
				if(msg.contains("똥개")) {
					System.out.println("똥개는 금지된 단어 입니다.");
					return; //메소드 끝내기
				}
			}
		}
		
		//aspect가 적용된 메소드가 호출 되기 직전에 할 작업은 proceed() 호출 전에 한다
		//proceed()를 호출해야  aspect가 적용된 메소드가 실행이 된다.
		joinPoint.proceed(); // throw exception해줌 
		//aspect가 적용된 메소드가 호출 된 후에 할 작업은 proceed() 호출 후에 한다
		
		//joinPoint.proceed();해주면 Messenger 클래스가 실행이 됨 . 딱 클래스 안에 메소드를 실행하기 딱!전에 ! aspect가 개입해서 검사를 하고 ! 똥개라는 단어가 있으면 메소드가
		// 호출됐음에도 불구하고 실행되지 않고 끝남.
		// 이런 작업은 around에서만 할 수 있음! (point가 있어서!! // before/after는 joinpoint가 없어서 이런 작업 불가능)
		System.out.println("aspect가 적용된 메소드가 리턴되었습니다.");
	}
	
	/*
	 * return type은 String이고
	 * get으로 시작은 메소드이고
	 * 메소드에 전달되는 인자는 없다
	 * java.lang 패키지에 있는 type은 패키지명 생략 가능
	 */
	
	@Around("execution(String com.example.aop.util.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable{
		//aspect가 적용된 메드를 수행하고 리턴되는 데이터 받아오기
		Object obj = joinPoint.proceed();
		//원래 타입으로 casting해서 조사해 볼 수 있음
		String a = (String)obj;
		//조사 후 아예 다른 값을 리턴해 줄 수 있음.
		return "놀자";
	}
	
}
