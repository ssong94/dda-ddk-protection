package com.spring.boot.ddaddk.protection.aop;


import com.spring.boot.ddaddk.protection.exception.DuplicateRequestException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class DuplicateRequestProtection {
	private final Set<String> requestSet = ConcurrentHashMap.newKeySet();

	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	private void aopTarget() {}

	@Around("aopTarget()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest request = getServletRequest();
		String sessionId = request.getSession().getId();
		String requestUri = request.getRequestURI();
		String key = sessionId + requestUri;

		checkDuplicateRequestAndAddKey(key);

		try {
			return joinPoint.proceed();
		} finally {
			removeRequest(key);
		}

	}

	private HttpServletRequest getServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
	}

	private void checkDuplicateRequestAndAddKey(String uri) {
		if(requestSet.contains(uri)) {
			throw new DuplicateRequestException();
		}

		requestSet.add(uri);
	}

	private void removeRequest(String uri) {
		requestSet.remove(uri);
	}




}
