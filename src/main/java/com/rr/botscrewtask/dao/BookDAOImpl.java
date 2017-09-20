package com.rr.botscrewtask.dao;

import com.rr.botscrewtask.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Book addBook(Book book) {
        getSession().persist(book);
        return book;
    }

    @Override
    public Book removeBook(Book book) {
        Query<Book> query = getSession().createQuery("delete from Book where bookName = :bkname and bookAuthor = :bkauthor");
        query.setParameter("bkname", book.getBookName());
        query.setParameter("bkauthor", book.getBookAuthor());
        int result = query.executeUpdate();
        if (result > 0) {
            return book;
        }
        return null;
    }

    @Override
    public Book removeBook(String bookName) {
        Book book = getSession().createQuery("from Book where bookName = :bkname", Book.class)
                .setParameter("bkname", bookName).getSingleResult();
        Query<Book> query = getSession().createQuery("delete from Book where bookName = :bkname");
        query.setParameter("bkname", bookName);
        int result = query.executeUpdate();
        if (result > 0) {
            return book;
        }
        return null;
    }

    @Override
    public Book editBook(Book book, String newBookName) {
        List<Book> books = getSession().createQuery("from Book where bookName = :bkname", Book.class).setParameter("bkname", newBookName).list();
        for (Book book1 : books)
            if (book.getBookAuthor().equals(book1.getBookAuthor()) && newBookName.equals(book1.getBookName()))
                return null;
        Book updatedBook = getSession().get(Book.class, book.getBookId());
        updatedBook.setBookName(newBookName);
        return book;
    }

    @Override
    public Book editBook(String bookName, String newBookName) {
        Book book = getSession().createQuery("from Book where bookName = :bkname", Book.class).setParameter("bkname", bookName).getSingleResult();
        List<Book> books = getSession().createQuery("from Book where bookName = :bkname", Book.class).setParameter("bkname", newBookName).list();
        for (Book book1 : books)
            if (book.getBookAuthor().equals(book1.getBookAuthor()) && newBookName.equals(book1.getBookName()))
                return null;
        getSession().saveOrUpdate(book);
        book.setBookName(newBookName);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        Query<Book> selectAllBooksQuery = getSession().createQuery("from Book");
        return selectAllBooksQuery.list();
    }

    @Override
    public List<Book> getBooksByName(String bookName) {
        Query<Book> selectBooksByNameQuery = getSession().createQuery("from Book where bookName = :bkname");
        selectBooksByNameQuery.setParameter("bkname", bookName);
        return selectBooksByNameQuery.list();
    }

    @Override
    public boolean isBookExists(Book book) {
        Query<Book> selectBookQuery = getSession().createQuery("from Book where bookName = :bkname and bookAuthor = :bkAuthor");
        selectBookQuery.setParameter("bkname", book.getBookName());
        selectBookQuery.setParameter("bkAuthor", book.getBookAuthor());
        Book someBook = selectBookQuery.uniqueResult();
        return someBook != null;
    }
}