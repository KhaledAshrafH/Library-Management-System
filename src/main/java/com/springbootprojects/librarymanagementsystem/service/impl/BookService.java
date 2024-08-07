package com.springbootprojects.librarymanagementsystem.service.impl;

import com.springbootprojects.librarymanagementsystem.model.dto.AddBookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.BookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.UpdateBookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDto> getAllBooks();
    BookDto findBookById(Long id);
    AddBookDto addBook(AddBookDto book);
    void updateBook(Long id,UpdateBookDto book);
    void deleteBook(Long id) throws Exception;
}
