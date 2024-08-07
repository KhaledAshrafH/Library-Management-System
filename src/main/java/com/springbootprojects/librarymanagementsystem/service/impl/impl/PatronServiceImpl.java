package com.springbootprojects.librarymanagementsystem.service.impl.impl;

import com.springbootprojects.librarymanagementsystem.exception.PatronServiceException;
import com.springbootprojects.librarymanagementsystem.model.dto.AddPatronDto;
import com.springbootprojects.librarymanagementsystem.model.dto.PatronDto;
import com.springbootprojects.librarymanagementsystem.model.dto.UpdatePatronDto;
import com.springbootprojects.librarymanagementsystem.model.entity.Patron;
import com.springbootprojects.librarymanagementsystem.model.mapper.PatronMapper;
import com.springbootprojects.librarymanagementsystem.repository.PatronRepository;
import com.springbootprojects.librarymanagementsystem.service.impl.PatronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;

    @Override
    public List<PatronDto> getAllPatrons() {
        try {
            List<Patron> patrons = patronRepository.findAll();
            return patronMapper.toDto(patrons);
        }
        catch (DataAccessException e) {
            log.error("Error fetching patrons from database", e);
            throw new PatronServiceException("Failed to retrieve patrons");
        }
        catch (Exception e) {
            log.error("Unexpected error while fetching patrons", e);
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    @Override
    public PatronDto findPatronById(Long id) {
        Patron patron = patronRepository.findById(id).orElse(null);
        return patronMapper.toDto(patron);
    }

    @Override
    public AddPatronDto addPatron(AddPatronDto patron) {
        Patron newPatron = patronMapper.toAddEntity(patron);
        Patron savedPatron = patronRepository.save(newPatron);
        return patronMapper.toAddDto(savedPatron);
    }

    @Override
    public void updatePatron(Long id, UpdatePatronDto patron) {
        Patron updatedPatron=patronMapper.toUpdateEntity(patron);
        Optional<Patron> checkPatron = patronRepository.findById(id);
        if(checkPatron.isPresent()){
            updatedPatron.setPatronId(id);
            updatedPatron.setDateOfBirth(checkPatron.get().getDateOfBirth());
            patronRepository.save(updatedPatron);
        }
        else{
            log.error("book is {}", patron);
        }
    }

    @Override
    public void deletePatron(Long id) throws Exception {
        if(patronRepository.findById(id).isPresent())
            patronRepository.deleteById(id);

        else
            throw new Exception("Id Not found");
    }
}
