package net.xerosoft.service;

import net.xerosoft.common.Page;
import net.xerosoft.common.Paginate;
import net.xerosoft.model.AuditLog;
import net.xerosoft.persistence.AuditLogPersistentMgr;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

@Singleton
public class AuditLogService {

    @Inject
    private AuditLogPersistentMgr auditLogPersistentMgr;

    public Paginate<AuditLog> getAll(Page page) {
        List<AuditLog> audits = auditLogPersistentMgr.getAll(page);
        return Paginate.of(audits, page.getOffset(), page.getLimit(), audits.size(), 0);
    }

    public AuditLog getById(Long id) {
        AuditLog auditLog = auditLogPersistentMgr.getById(id);
        return auditLog;
    }
}
