package com.springbootprojects.librarymanagementsystem.repository;

import com.springbootprojects.librarymanagementsystem.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
