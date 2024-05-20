package com.thereadingroom.service;

import com.thereadingroom.model.db.BookEntity;

import java.awt.print.Book;

public interface BookService {

    BookEntity save(BookEntity book);
}
