package net.xerosoft.persistence;

import net.xerosoft.NotFoundException;
import net.xerosoft.common.Page;
import net.xerosoft.model.Quote;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class QuotePersistentMgr {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Quote> getAll() {
        return entityManager.createNamedQuery("Quote.findAll", Quote.class).getResultList();
    }

    public List<Quote> getAll(Page page) {
        return entityManager.createNamedQuery("Quote.findAll", Quote.class)
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getLimit()).getResultList();
    }

    public Quote getById(Long id) {
        Optional<Quote> result = entityManager.createNamedQuery("Quote.findById", Quote.class)
                .setParameter("id", id).getResultList().stream().findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException();
        }
    }

    public List<Quote> getByAuthor(String author, Page page) {
        return entityManager.createNamedQuery("Quote.findByAuthor", Quote.class)
                .setParameter("author", author)
                .setFirstResult(page.getOffset())
                .setMaxResults(page.getLimit()).getResultList();
    }

    @Transactional
    public Quote create(Quote quote) {
        entityManager.persist(quote);
        return quote;
    }
}
