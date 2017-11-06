package net.marcioguimaraes.pure.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {
	
	// PV:IFCOND(pv:hasFeature('MONITOR'))
    @AfterReturning("execution(* *..*Controller.*(..)))")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("================================================");
        System.out.println("Completed: " + joinPoint);
        System.out.println("================================================");
    }
    // PV:ENDCOND

    @Before("call()")
    public void bla(JoinPoint joinPoint) {

    }

}
