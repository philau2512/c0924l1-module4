package com.example.app_borrow_book.service;

import com.example.app_borrow_book.model.Book;

import java.util.List;

public interface ILibraryService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    String borrowBook(Long bookId);
    void returnBook(String code);
}
