package com.rr.botscrewtask.dao;

import com.rr.botscrewtask.model.Book;

import java.util.List;

public interface BookDAO {
    Book addBook(Book book);

    Book removeBook(Book book);

    Book removeBook(String bookName);

    Book editBook(Book book, String newBookName);

    Book editBook(String bookName, String newBookName);

    List<Book> getAllBooks();

    List<Book> getBooksByName(String bookName);

    boolean isBookExists(Book book);
}