package net.marcioguimaraes.pure.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

    @AfterReturning("execution(* *..*Controller.*(..)))")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("================================================");
        System.out.println("Completed: " + joinPoint);
        System.out.println("================================================");
    }

    @Before("call()")
    public void bla(JoinPoint joinPoint) {

    }

}
