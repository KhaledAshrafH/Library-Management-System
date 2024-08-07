package com.springbootprojects.librarymanagementsystem.model.mapper;

import com.springbootprojects.librarymanagementsystem.model.dto.*;
import com.springbootprojects.librarymanagementsystem.model.entity.Patron;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PatronMapper {
    PatronDto toDto(Patron patron);
    Patron toEntity(PatronDto patronDto);

    AddPatronDto toAddDto(Patron patron);
    Patron toAddEntity(AddPatronDto patronDto);

    UpdatePatronDto toUpdateDto(Patron patron);
    Patron toUpdateEntity(UpdatePatronDto updatePatronDto);

    List<PatronDto> toDto(List<Patron> patrons);
    List<Patron> toEntity(List<PatronDto> patronDtos);
}
