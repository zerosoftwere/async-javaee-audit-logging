package net.xerosoft.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name = "Quote.count", query = "SELECT COUNT(q) FROM Quote q")
@NamedQuery(name = "Quote.findAll", query = "SELECT q FROM Quote q")
@NamedQuery(name = "Quote.findById", query = "SELECT q FROM Quote q WHERE q.id = :id")
@NamedQuery(name = "Quote.findByAuthor", query = "SELECT q FROM Quote q WHERE LOWER(q.author) LIKE CONCAT('%', LOWER(:author), '%')")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quote;
    private String author;
}
