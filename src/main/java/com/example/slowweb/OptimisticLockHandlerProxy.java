package com.example.slowweb;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.StaleObjectStateException;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OptimisticLockHandlerProxy implements Ordered {

   private static final int DEFAULT_MAX_RETRIES = 2;

   private int maxRetries = DEFAULT_MAX_RETRIES;

   /** 
    * This is the method which I would like to execute
    * before a selected method execution.
    */
   @Around("@annotation(com.example.slowweb.OptimisticLockSafe)")
   public Object  beforeAdvice(ProceedingJoinPoint pjp) throws Throwable {
      int numAttempts = 0;
      RuntimeException lockFailureException;
      do {
         numAttempts++;
         try {
            return pjp.proceed();
         }
         catch(PessimisticLockingFailureException | StaleObjectStateException ex) {
            if (numAttempts <= this.maxRetries)
             System.out.println("PessimisticLockingFailureException Occured:: Retrying attempt "+numAttempts);
            lockFailureException = ex;
         }
      } while(numAttempts <= this.maxRetries);
      throw lockFailureException;

   }

   @Override
   public int getOrder() {
      return 100;
   }
}
