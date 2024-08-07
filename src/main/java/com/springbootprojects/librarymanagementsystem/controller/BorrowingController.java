package com.springbootprojects.librarymanagementsystem.controller;

import com.springbootprojects.librarymanagementsystem.exception.*;
import com.springbootprojects.librarymanagementsystem.model.entity.BorrowingRecord;
import com.springbootprojects.librarymanagementsystem.service.impl.BorrowingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor

public class BorrowingController {
    private final BorrowingService borrowingService;


    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecord borrowingRecord = borrowingService.borrowBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        }
        catch (BookAlreadyBorrowedException e) {
            log.error("Book already borrowed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("BOOK_ALREADY_BORROWED", e.getMessage()) {
                    });
        }
        catch (BookNotFoundException | PatronNotFoundException e) {
            log.error("Book or patron not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            log.error("Unexpected error occurred:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            borrowingService.returnBook(bookId, patronId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (BookNotFoundException | PatronNotFoundException e) {
            log.error("Book or Patron not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BorrowingRecordNotFoundException e) {
            log.error("Borrowing record not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("BORROWING_RECORD_NOT_FOUND", e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error occurred:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
