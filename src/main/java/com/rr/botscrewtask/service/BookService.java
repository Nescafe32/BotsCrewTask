package com.rr.botscrewtask.service;

import com.rr.botscrewtask.dao.BookDAO;
import com.rr.botscrewtask.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public void addBook(Book book) {
        if (!bookDAO.isBookExists(book)) {
            bookDAO.addBook(book);
            System.out.println("book " + book.getBookAuthor() + " " + book.getBookName() + " was added");
            return;
        }
        System.out.println("book with author " + book.getBookAuthor() + " and name " + book.getBookName() + " already exists");
    }

    public void removeBook(String bookName) {
        List<Book> books = bookDAO.getBooksByName(bookName);
        switch (books.size()) {
            case 0:
                System.out.println("There aren't books with the name " + bookName);
                break;
            case 1:
                bookDAO.removeBook(bookName);
                System.out.println("Book " + books.get(0).getBookAuthor() + " " + books.get(0).getBookName() + " was removed");
                break;
            default:
                System.out.println("We have few books with such name. Please, choose one by typing a number of book");
                for (int i = 0; i < books.size(); ) {
                    Book someBook = books.get(i);
                    System.out.println("\t" + ++i + ". " + someBook.getBookAuthor() + " \"" + someBook.getBookName() + "\"");
                }

                if (scanner.hasNextInt()) {
                    int userChoise = scanner.nextInt();
                    Book book = bookDAO.removeBook(books.get(userChoise - 1));
                    if (book != null)
                        System.out.println("Book " + book + " was removed");
                }
        }
    }

    public void editBook(String bookName) {
        String newBookName = null;

        List<Book> books = bookDAO.getBooksByName(bookName);
        switch (books.size()) {
            case 0:
                System.out.println("There aren't books with the name " + bookName);
                break;
            case 1:
                System.out.println("Please, write new book name");
                newBookName = scanner.nextLine();
                if (bookDAO.editBook(bookName, newBookName) != null)
                    System.out.println("Book " + books.get(0).getBookAuthor() + " " + books.get(0).getBookName() + " was edited. New name - " + newBookName);
                else {
                    System.out.println("Cannot update book, because this author already has this book");
                }
                break;
            default:
                System.out.println("We have few books with such name. Please, choose one by typing a number of book");
                for (int i = 0; i < books.size(); ) {
                    Book someBook = books.get(i);
                    System.out.println("\t" + ++i + ". " + someBook.getBookAuthor() + " \"" + someBook.getBookName() + "\"");
                }
                int userChoise = Integer.MIN_VALUE;
                if (scanner.hasNextInt()) {
                    userChoise = scanner.nextInt();
                }
                scanner.nextLine();
                System.out.println("Please, write new book name");
                if (scanner.hasNext()) {
                    newBookName = scanner.nextLine();
                }
                if (bookDAO.editBook(books.get(userChoise - 1), newBookName) != null)
                    System.out.println("Book " + books.get(0).getBookAuthor() + " " + books.get(0).getBookName() + " was edited. New name - " + newBookName);
                else {
                    System.out.println("Cannot update book, because this author already has this book");
                }
        }
    }

    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        System.out.println("Our books:");
        bookDAO.getAllBooks()
                .stream()
                .sorted((book1, book2) -> book1.getBookName().compareTo(book2.getBookName()))
                .forEach((book) -> {
                    books.add(book);
                    System.out.println("\t" + book);
                });
        if (books.size() == 0)
            System.out.println("\tNot yet, but you may add some");
        return books;
    }
}