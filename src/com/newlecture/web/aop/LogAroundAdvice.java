package com.newlecture.web.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class LogAroundAdvice implements org.aopalliance.intercept.MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		
		StopWatch sw = new StopWatch();
		
		System.out.println("[�ð�üũ] ȣ�����");
		sw.start();
		
		Object result = method.proceed(); // ���� ���ڴ��� ȣ���ϴ� ���� ����Ʈ����Ʈ
		
		sw.stop();
		System.out.println("[�ð�üũ] ȣ�ⳡ");
		System.out.println("[TIMELOG] Method :" + method.getMethod().getName() +"is finished");
		System.out.println("[TIMELOG] Process Time :" + sw.getTotalTimeMillis());
		
		return result;
	}

	
}
