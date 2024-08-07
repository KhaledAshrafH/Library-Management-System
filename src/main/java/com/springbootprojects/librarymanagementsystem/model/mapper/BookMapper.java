package com.springbootprojects.librarymanagementsystem.model.mapper;

import com.springbootprojects.librarymanagementsystem.model.dto.AddBookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.BookDto;
import com.springbootprojects.librarymanagementsystem.model.dto.UpdateBookDto;
import com.springbootprojects.librarymanagementsystem.model.entity.Book;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
    Book toEntity(BookDto bookDto);

    AddBookDto toAddDto(Book book);
    Book toAddEntity(AddBookDto bookDto);

    UpdateBookDto toUpdateDto(Book book);
    Book toUpdateEntity(UpdateBookDto updateBookDto);

    List<BookDto> toDto(List<Book> books);
    List<Book> toEntity(List<BookDto> bookDtos);
}
