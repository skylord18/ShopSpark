//package com.shopspark.ShopSpark.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//import java.time.Instant;
//
////import static org.hibernate.query.sqm.tree.SqmNode.log;
//
//@Slf4j
//@Aspect
//@Component
//public class loggerAspect {
//    @Around("execution(* com.shopspark.ShopSpark..*.*(..))")
//    public Object log(ProceedingJoinPoint joinpoint) throws Throwable {
//        log.info(joinpoint.getSignature().toString() + "Execution Start");
//        Instant startTime = Instant.now();
//        Object returnObj = joinpoint.proceed();
//        Instant endTime = Instant.now();
//        log.info("Total Time Taken to execute the method " + joinpoint.getSignature().toString()+"is " + Duration.between(startTime, endTime).toMillis()+ "ms");
//        log.info(joinpoint.getSignature().toString() + "Execution Ended");
//        return returnObj;
//    }
//    @AfterThrowing(value = "execution(* com.shopspark.ShopSpark..*.*(..))", throwing = "ex")
//    public void logExceptions(ProceedingJoinPoint joinpoint, Exception ex){
//        log.error(joinpoint.getSignature().toString() + " Threw Exception Due to "+ ex.getMessage());
//    }
//}
