package com.springbootprojects.librarymanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookDto {
    private Long bookId;
    private String isbn;
    private String title;
    private String description;
    private String author;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
