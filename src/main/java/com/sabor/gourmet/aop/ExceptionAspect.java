package com.sabor.gourmet.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @AfterThrowing(pointcut = "execution(* com.sabor.gourmet.service.*.*(..))", throwing = "ex")
    public void manejarExcepcion(JoinPoint joinPoint, Exception ex) {
        log.error("❌ AOP - Error en {}.{}(): {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }
}
