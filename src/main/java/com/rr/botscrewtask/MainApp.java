package com.rr.botscrewtask;

import com.rr.botscrewtask.configuration.AppConfig;
import com.rr.botscrewtask.controller.BookController;
import com.rr.botscrewtask.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = ctx.getBean("bookService", BookService.class);
        new BookController().startOperations(bookService);
    }
}