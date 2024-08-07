package com.springbootprojects.librarymanagementsystem.repository;

import com.springbootprojects.librarymanagementsystem.model.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
}
