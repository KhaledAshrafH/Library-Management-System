package com.springbootprojects.librarymanagementsystem.service.impl.impl;


import com.springbootprojects.librarymanagementsystem.exception.BookAlreadyBorrowedException;
import com.springbootprojects.librarymanagementsystem.exception.BookNotFoundException;
import com.springbootprojects.librarymanagementsystem.exception.BorrowingRecordNotFoundException;
import com.springbootprojects.librarymanagementsystem.exception.PatronNotFoundException;
import com.springbootprojects.librarymanagementsystem.model.entity.Book;
import com.springbootprojects.librarymanagementsystem.model.entity.BorrowingRecord;
import com.springbootprojects.librarymanagementsystem.model.entity.Patron;
import com.springbootprojects.librarymanagementsystem.repository.BookRepository;
import com.springbootprojects.librarymanagementsystem.repository.BorrowingRepository;
import com.springbootprojects.librarymanagementsystem.repository.PatronRepository;
import com.springbootprojects.librarymanagementsystem.service.impl.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) throws BookNotFoundException, PatronNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron with ID " + patronId + " not found"));

        if (book.isBorrowed()) {
            throw new BookAlreadyBorrowedException("Book with ID " + bookId + " is already borrowed");
        }
        book.setBorrowed(true);
        bookRepository.save(book);

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowedAt(LocalDateTime.now());
        borrowingRecord.setDueDate(LocalDateTime.now().plusDays(14)); // Set due date 14 days from now
        return borrowingRepository.save(borrowingRecord);
    }

    public void returnBook(Long bookId, Long patronId) throws BookNotFoundException, PatronNotFoundException, BorrowingRecordNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron with ID " + patronId + " not found"));


        book.setBorrowed(false);
        bookRepository.save(book);

        Optional<BorrowingRecord> borrowingRecordOptional = Optional.ofNullable(borrowingRepository.findByBookAndPatron(book, patron));
        BorrowingRecord borrowingRecord = borrowingRecordOptional.orElseThrow(() -> new BorrowingRecordNotFoundException("Borrowing record not found for book " + bookId + " and patron " + patronId));

        borrowingRecord.setReturnedAt(LocalDateTime.now());
        borrowingRecord.getBook().setBorrowed(false);
        borrowingRepository.save(borrowingRecord);
    }
}
