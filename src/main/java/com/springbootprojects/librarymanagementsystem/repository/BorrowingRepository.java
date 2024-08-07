package com.springbootprojects.librarymanagementsystem.repository;

import com.springbootprojects.librarymanagementsystem.model.entity.Book;
import com.springbootprojects.librarymanagementsystem.model.entity.BorrowingRecord;
import com.springbootprojects.librarymanagementsystem.model.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BorrowingRepository extends JpaRepository<BorrowingRecord,Long> {
    BorrowingRecord findByBookAndPatron(Book book, Patron patron);
}
