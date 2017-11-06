package net.marcioguimaraes.pure.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

    private static final Logger log = LoggerFactory.getLogger(ServiceMonitor.class);
	
	// PV:IFCOND(pv:hasFeature('MONITOR'))
    @AfterReturning("execution(* *..*Controller.*(..)))")
    public void logServiceAccess(JoinPoint joinPoint) {
        log.info("================================================");
        log.info("Completed: " + joinPoint);
        log.info("================================================");
    }
    // PV:ENDCOND

}
