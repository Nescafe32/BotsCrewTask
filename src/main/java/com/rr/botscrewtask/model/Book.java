package com.rr.botscrewtask.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@SelectBeforeUpdate
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long bookId;
    @Column(name = "BOOK_NAME")
    @Getter
    @Setter
    private String bookName;
    @Column(name = "BOOK_AUTHOR")
    @Getter
    @Setter
    private String bookAuthor;

    public Book(String bookAuthor, String bookName) {
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return bookAuthor + " " + bookName;
    }
}