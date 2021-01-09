package gr.bill.springbootelapsedtimestarter.elapsedtime.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.Instant;
import java.util.Arrays;

@Slf4j
@Aspect
public class ElapsedLogAspect {


    @Around("@annotation(gr.bill.springbootelapsedtimestarter.elapsedtime.logging.ElapsedLog)")
    public Object logElapsedTimeAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Start executing: {}.{}() with argument = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                    proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
        }
        try {
            long start = Instant.now().toEpochMilli();
            Object result = proceedingJoinPoint.proceed();
            long end = Instant.now().toEpochMilli();
            log.info("Method: {}.{}() takes {} milliseconds to execute", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                    proceedingJoinPoint.getSignature().getName(), end - start);

            if (log.isDebugEnabled()) {
                log.debug("Return from : {}.{}() with result = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                        proceedingJoinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(proceedingJoinPoint.getArgs()),
                    proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());

            throw e;
        }
    }
}