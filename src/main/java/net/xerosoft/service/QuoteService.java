package net.xerosoft.service;

import net.xerosoft.common.Page;
import net.xerosoft.common.Paginate;
import net.xerosoft.model.Quote;
import net.xerosoft.persistence.QuotePersistentMgr;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class QuoteService implements QuoteContract {

    @Inject
    private QuotePersistentMgr quotePersistentMgr;

    @Override
    public Paginate<Quote> getAll(Page page) {
        List<Quote> quotes = quotePersistentMgr.getAll(page);
        return Paginate.of(quotes, page.getOffset(), page.getLimit(), quotes.size(), 0);
    }

    @Override
    public Quote getById(Long id) {
        return quotePersistentMgr.getById(id);
    }

    @Override
    public Paginate<Quote> getByAuthor(String author, Page page) {
        List<Quote> quotes = quotePersistentMgr.getByAuthor(author, page);
        return Paginate.of(quotes, page.getOffset(), page.getLimit(), quotes.size(), 0);
    }

    @Override
    public void create(Quote quote) {
        quotePersistentMgr.create(quote);
    }
}
