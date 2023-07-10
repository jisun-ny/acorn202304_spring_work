package com.example.aop.util;

public class Messenger {
	public void sendGreeting(String msg) {
		System.out.println("오늘의 인사:" + msg);
	}
	
	public String getMessage() {
		System.out.println("getMessage() 메소드가 수행됩니다.");
		return "열심히 공부하자";
	}
}


/*
 * Messenger m1 = ctx.getBean(Messenger.class)
 * String result = m1.getMessege()
 * 
 * --> Messenger 객체의 getMessage()가 수행되고
 * 열심히 공부하자가 m1.getMessege()로 들어와야함.
 * 
 * before: getMessage()가 수행되기 전
 * after: getMessage()가 수행된 후
 * around: 양쪽을 다 개입할 수 있음
 * 
 * 
 * MessengerAspect객체의 checjReturn메소드의 Object obj = joinPoint.proceed();가 실행되면 
 * Messenger 객체getMessage()가 수행되는 것임
 */ 