package net.xerosoft.interceptor;

import lombok.SneakyThrows;
import net.xerosoft.model.AuditLog;
import net.xerosoft.persistence.AuditLogPersistentMgr;
import net.xerosoft.service.ExecutorService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Interceptor
@AuditLogger
public class AuditLoggerInterceptor {

    @Context
    private HttpServletRequest request;

    @Inject
    private ExecutorService executorService;

    @Inject
    private AuditLogPersistentMgr auditLogPersistentMgr;

    @AroundInvoke
    public Object computeLatency(InvocationContext context) throws Exception {
        AuditLogger auditor = context.getMethod().getAnnotation(AuditLogger.class);
        AuditLog log = new AuditLog();
        log.setDescription(auditor.description());
        log.setEvent(auditor.event());
        log.setIpAddress(request.getHeader("X-Forwarded-For") == null ?
            request.getRemoteAddr() : request.getHeader("X-Forwarded-For"));

        try {
            Object returnValue = context.proceed();
            log.setStatus(AuditLog.Status.SUCCESS);
            return returnValue;
        } catch (Exception e) {
            log.setStatus(AuditLog.Status.FAIL);
            throw e;
        } finally {
            asyncPersist(log);
        }
    }

    @SneakyThrows
    private void asyncPersist(final AuditLog log) {
        executorService.submit(() -> {
            auditLogPersistentMgr.create(log);
        });
    }
}
