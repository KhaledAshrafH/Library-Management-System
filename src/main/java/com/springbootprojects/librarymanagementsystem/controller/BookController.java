package com.springbootprojects.librarymanagementsystem.controller;

import com.springbootprojects.librarymanagementsystem.exception.BookAlreadyExistsException;
import com.springbootprojects.librarymanagementsystem.exception.BookNotFoundException;
import com.springbootprojects.librarymanagementsystem.exception.BookServiceException;
import com.springbootprojects.librarymanagementsystem.exception.InvalidDataException;
import com.springbootprojects.librarymanagementsystem.model.dto.AddBookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.BookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.UpdateBookDto;
import com.springbootprojects.librarymanagementsystem.service.impl.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/books")
@RequiredArgsConstructor

public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<BookDto> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (BookServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
        try {
            BookDto bookDto = bookService.findBookById(id);
            return ResponseEntity.ok(bookDto);
        }
        catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book not found");
        }
    }


    @PostMapping("")
    public ResponseEntity<?> addBook(@RequestBody AddBookDto book) {
        try {
            AddBookDto addedBook = bookService.addBook(book);
            return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (BookAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public String UpdateBook(@PathVariable("id") Long id,@RequestBody UpdateBookDto book){
        bookService.updateBook(id,book);
        return "Book updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) throws Exception {
        bookService.deleteBook(id);
        return "Book deleted Successfully";
    }

}
