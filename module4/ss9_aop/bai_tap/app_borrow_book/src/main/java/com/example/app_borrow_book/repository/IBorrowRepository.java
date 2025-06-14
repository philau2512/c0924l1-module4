package com.example.app_borrow_book.repository;

import com.example.app_borrow_book.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBorrowRepository extends JpaRepository<BorrowRecord, String> {
}
