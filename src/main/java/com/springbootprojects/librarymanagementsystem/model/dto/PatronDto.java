package com.springbootprojects.librarymanagementsystem.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatronDto {
    private Long patronId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Long libraryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
