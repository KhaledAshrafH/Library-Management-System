package com.springbootprojects.librarymanagementsystem.service.impl;

import com.springbootprojects.librarymanagementsystem.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatronService {
    List<PatronDto> getAllPatrons();
    PatronDto findPatronById(Long id);
    AddPatronDto addPatron(AddPatronDto book);
    void updatePatron(Long id, UpdatePatronDto book);
    void deletePatron(Long id) throws Exception;
}
