package net.marcioguimaraes.pure.cache;

import net.marcioguimaraes.pure.monitor.ServiceMonitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
@Aspect
public class ServiceCache {

    private static final Logger log = LoggerFactory.getLogger(ServiceMonitor.class);
    private final ConcurrentHashMap CACHE = new ConcurrentHashMap();

    // PV:IFCOND(pv:hasFeature('CACHE'))
    @Around("execution(* *..*Controller.get(..)))")
    public Object logServiceAccess(ProceedingJoinPoint joinPoint) {
        log.info("################################################");
        log.info("CACHE : " + joinPoint);


        Object object = null;
        Object[] args = joinPoint.getArgs();
        Long id = (Long) args[0];

        if (!CACHE.containsKey(id)) {
            log.info("CACHE MISS: " + id);
            try {
                object = joinPoint.proceed();
                CACHE.put(id, object);
            } catch (Throwable t){
                log.error(t.getMessage());
            }
        } else {
            log.info("CACHE HIT: " + id);
        }
        log.info("################################################");
        return CACHE.get(id);
    }
    // PV:ENDCOND
}
