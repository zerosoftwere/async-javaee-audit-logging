package net.xerosoft.persistence;

import net.xerosoft.common.Page;
import net.xerosoft.model.Task;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class TaskPersistentMgr {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Task> getAll() {
        return entityManager.createNamedQuery("Task.findAll").getResultList();
    }

    public List<Task> getAll(Page page) {
        return entityManager.createNamedQuery("Task.findAll")
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getLimit()).getResultList();
    }

    public Task getById(Long id) {
        return (Task) entityManager.createNamedQuery("Task.findById")
                .setParameter("id", id).getSingleResult();
    }
}
