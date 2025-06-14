package com.example.app_borrow_book.service;

import com.example.app_borrow_book.model.Book;
import com.example.app_borrow_book.model.BorrowRecord;
import com.example.app_borrow_book.repository.IBookRepository;
import com.example.app_borrow_book.repository.IBorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LibraryService implements ILibraryService {
    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IBorrowRepository borrowRepository;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public String borrowBook(Long bookId) {
        Book book = getBookById(bookId);
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book not available");
        }
        book.setQuantity(book.getQuantity() - 1);
        String code = String.format("%05d", new Random().nextInt(100000));
        BorrowRecord record = new BorrowRecord();
        record.setBorrowCode(code);
        record.setBook(book);
        borrowRepository.save(record);
        bookRepository.save(book);
        return code;
    }

    @Override
    public void returnBook(String code) {
        BorrowRecord record = borrowRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Invalid code"));

        Book book = record.getBook();
        book.setQuantity(book.getQuantity() + 1);
        borrowRepository.delete(record);
        bookRepository.save(book);
    }
}
