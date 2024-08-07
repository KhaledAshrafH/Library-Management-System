package com.springbootprojects.librarymanagementsystem.service.impl;

import com.springbootprojects.librarymanagementsystem.model.entity.BorrowingRecord;
import org.springframework.stereotype.Service;

@Service
public interface BorrowingService {
    BorrowingRecord borrowBook(Long bookId, Long patronId);
    void returnBook(Long bookId, Long patronId);
}
