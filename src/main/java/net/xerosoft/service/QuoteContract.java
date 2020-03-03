package net.xerosoft.service;

import net.xerosoft.common.Page;
import net.xerosoft.common.Paginate;
import net.xerosoft.model.Quote;
import net.xerosoft.model.Task;

import javax.inject.Singleton;

@Singleton
public interface QuoteContract {
    Paginate<Quote> getAll(Page page);
    Paginate<Quote> getByAuthor(String author, Page page);
    Quote getById(Long id);
    void create(Quote quote);
}
