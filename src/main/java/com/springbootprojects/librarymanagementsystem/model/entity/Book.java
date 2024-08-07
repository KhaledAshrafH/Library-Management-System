package com.springbootprojects.librarymanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@Builder
public class Book {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @Id
    private Long bookId;

    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    @Column(name = "description")
    private String description;
    @Column(name = "edition")
    private String edition;
    @Column(name = "genre")
    private String genre;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_borrowed")
    private boolean isBorrowed;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
