package com.springbootprojects.librarymanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddBookDto {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer numberOfPages;
    private String description;
    private String edition;
    private String genre;
    private Double price;
}
