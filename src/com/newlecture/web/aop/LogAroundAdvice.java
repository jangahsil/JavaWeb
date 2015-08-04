package com.newlecture.web.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class LogAroundAdvice implements org.aopalliance.intercept.MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		
		StopWatch sw = new StopWatch();
		
		System.out.println("[시간체크] 호출시작");
		sw.start();
		
		Object result = method.proceed(); // 실제 왕자님을 호출하는 실제 조인트포인트
		
		sw.stop();
		System.out.println("[시간체크] 호출끝");
		System.out.println("[TIMELOG] Method :" + method.getMethod().getName() +"is finished");
		System.out.println("[TIMELOG] Process Time :" + sw.getTotalTimeMillis());
		
		return result;
	}

	
}
