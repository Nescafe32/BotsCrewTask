package com.rr.botscrewtask.controller;

import com.rr.botscrewtask.model.Book;
import com.rr.botscrewtask.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class BookController {
    @Autowired
    private BookService bookService;

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void removeBook(String bookName) {
        bookService.removeBook(bookName);
    }

    public void editBook(String bookName) {
        bookService.editBook(bookName);
    }

    public void findAllBooks() {
        bookService.findAllBooks();
    }

    public void startOperations(BookService bookService) {
        this.bookService = bookService;
        Scanner scanner = new Scanner(System.in);
        String choise = scanner.nextLine();
        String bookName;
        while (!choise.equals("stop")) {
            String[] input = choise.trim().split(" ");

            if (input.length == 2 && input[0].equals("all") && input[1].equals("books"))
                findAllBooks();

            else {
                switch (input[0]) {
                    case "add":
                        addPreparations(choise);
                        break;
                    case "remove":
                        bookName = choise.substring(choise.indexOf(" ")).trim();
                        removeBook(bookName);
                        break;
                    case "edit":
                        bookName = choise.substring(choise.indexOf(" ")).trim();
                        editBook(bookName);
                        break;
                    default:
                        System.out.println("Unknown operation. Please, try again!");
                }
            }
            choise = scanner.nextLine();
        }
        scanner.close();
    }

    private void addPreparations(String choise) {
        if (StringUtils.ordinalIndexOf(choise, "\"", 1) < 0 || StringUtils.ordinalIndexOf(choise, "\"", 2) < 0) {
            System.out.println("Book name must be in quotes");
            return;
        }
        String authorName = choise.trim().substring(StringUtils.ordinalIndexOf(choise, " ", 1), choise.indexOf("\""));
        if (authorName.equals(" "))
            authorName = choise.trim().substring(StringUtils.ordinalIndexOf(choise, "\"", 2) + 1);
        String bookName = choise.substring(StringUtils.ordinalIndexOf(choise, "\"", 1) + 1, StringUtils.ordinalIndexOf(choise, "\"", 2)).trim();
        if (bookName.length() < 1) {
            System.out.println("Book name must be greater than zero");
            return;
        }
        addBook(new Book(authorName, bookName));
    }
}