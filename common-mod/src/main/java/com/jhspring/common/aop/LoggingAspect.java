package com.jhspring.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* com.jhspring..controller..*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();

        // 요청 파라미터 로깅
        Object[] args = joinPoint.getArgs();
        log.info("[REQUEST] {} {} | ARGS: {}", method, uri, Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("[EXCEPTION] {} {}", method, uri, e);
            throw e;
        }

        long elapsed = System.currentTimeMillis() - start;
        log.info("[RESPONSE] {} {} | RESULT: {} | TIME: {}ms", method, uri, result, elapsed);

        return result;
    }
}
