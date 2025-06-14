package com.example.app_borrow_book.repository;

import com.example.app_borrow_book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
