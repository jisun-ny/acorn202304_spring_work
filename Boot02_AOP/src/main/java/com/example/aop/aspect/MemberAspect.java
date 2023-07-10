package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.aop.util.MemberDto;

@Aspect
@Component
public class MemberAspect {
	//스프링이 빈으로 관리하는 모든 것중에서 리턴타입이 멤버디티오면서 get으로 시작하는 모든 메소드
	@Around("execution(com.example.aop.util.MemberDto get*(..))")
	public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect가 적용된 메소드를 수행하고 리턴되는 값을(참조값) 받아온다.
		Object obj = joinPoint.proceed();
		//원래 type으로 casting
		MemberDto dto = (MemberDto)obj;
		//필드 값을 넣어준다.
		dto.setNum(1);
		dto.setName("김구라");
		dto.setAddr("노량진");
		//joinPoint.proceed() 메소드가 리턴한 참조값을 받아서 그대로 리턴해준다.
		return dto;
		
	}
}
