package com.springbootprojects.librarymanagementsystem.service.impl.impl;

import com.springbootprojects.librarymanagementsystem.exception.BookAlreadyExistsException;
import com.springbootprojects.librarymanagementsystem.exception.BookNotFoundException;
import com.springbootprojects.librarymanagementsystem.exception.BookServiceException;
import com.springbootprojects.librarymanagementsystem.exception.InvalidDataException;
import com.springbootprojects.librarymanagementsystem.model.dto.AddBookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.BookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.UpdateBookDto;
import com.springbootprojects.librarymanagementsystem.model.entity.Book;
import com.springbootprojects.librarymanagementsystem.model.mapper.BookMapper;
import com.springbootprojects.librarymanagementsystem.repository.BookRepository;
import com.springbootprojects.librarymanagementsystem.service.impl.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return bookMapper.toDto(books);
        }
        catch (DataAccessException e) {
            log.error("Error fetching books from database", e);
            throw new BookServiceException("Failed to retrieve books");
        }
        catch (Exception e) {
            log.error("Unexpected error while fetching books", e);
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    @Override
    public BookDto findBookById(Long id) {
        try {
            Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
            return bookMapper.toDto(book);
        } catch (EmptyResultDataAccessException e) {
            log.error("Book not found with ID: {}", id, e);
            throw new BookNotFoundException("Book not found");
        } catch (Exception e) {
            log.error("Error fetching book with ID: {}", id, e);
            throw new RuntimeException("An unexpected error occurred while fetching book", e);
        }
    }

    @Override
    public AddBookDto addBook(AddBookDto book) {
        validateBook(book);
        Book newBook = bookMapper.toAddEntity(book);
        try {
            Book savedBook = bookRepository.save(newBook);
            return bookMapper.toAddDto(savedBook);
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistsException("Book with ISBN or title already exists");
        }
    }

    @Override
    public void updateBook(Long id, UpdateBookDto book) {
        Book updatedBook=bookMapper.toUpdateEntity(book);
        Optional<Book> checkBook = bookRepository.findById(id);
        if(checkBook.isPresent()){
            updatedBook.setBookId(id);
            bookRepository.save(updatedBook);
        }
        else{
            log.error("book is {}", book);
        }
    }

    @Override
    public void deleteBook(Long id) throws Exception {
        if(bookRepository.findById(id).isPresent())
            bookRepository.deleteById(id);

        else
            throw new Exception("Id Not found");
    }

    private void validateBook(AddBookDto bookDto) throws InvalidDataException {

        // Title validation
        if (bookDto.getTitle() == null || bookDto.getTitle().trim().isEmpty()) {
            throw new InvalidDataException("Title cannot be empty");
        }

        if (bookDto.getAuthor() == null || bookDto.getAuthor().trim().isEmpty()) {
            throw new InvalidDataException("Author cannot be empty");
        }

        if (bookDto.getPrice() == null || bookDto.getPrice() < 0) {
            throw new InvalidDataException("Price must be non-negative");
        }
    }
}



