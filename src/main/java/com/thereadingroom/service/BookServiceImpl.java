package com.thereadingroom.service;

import com.thereadingroom.model.db.BookEntity;
import com.thereadingroom.repository.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(BookEntity book) {
      return this.bookRepository.saveAndFlush(book);
    }
}
