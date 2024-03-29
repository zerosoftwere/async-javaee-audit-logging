package net.xerosoft.persistence;

import net.xerosoft.NotFoundException;
import net.xerosoft.common.Page;
import net.xerosoft.model.AuditLog;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuditLogPersistentMgr {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AuditLog> getAll(Page page) {
        return entityManager.createNamedQuery("AuditLog.findAll", AuditLog.class)
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getLimit()).getResultList();
    }

    public AuditLog getById(Long id) {
        Optional<AuditLog> result = entityManager.createNamedQuery("AuditLog.findById", AuditLog.class)
                    .setParameter("id", id).getResultList().stream().findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException();
        }
    }

    @Transactional
    public AuditLog create(AuditLog auditLog) {
        entityManager.persist(auditLog);
        return auditLog;
    }
}
