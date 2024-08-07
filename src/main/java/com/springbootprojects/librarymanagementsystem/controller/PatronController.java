package com.springbootprojects.librarymanagementsystem.controller;
import com.springbootprojects.librarymanagementsystem.exception.PatronServiceException;
import com.springbootprojects.librarymanagementsystem.model.dto.*;
import com.springbootprojects.librarymanagementsystem.service.impl.PatronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(path = "/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;


    @GetMapping("")
    public ResponseEntity<?> getAllPatrons() {
        try {
            List<PatronDto> patrons = patronService.getAllPatrons();
            return ResponseEntity.ok(patrons);
        }
        catch (PatronServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");

        }
    }

    @GetMapping("/{id}")
    public Optional<PatronDto> getPatronById(@PathVariable("id") Long id){
        return Optional.ofNullable(patronService.findPatronById(id));
    }

    @PostMapping("")
    public AddPatronDto addPatron(@RequestBody AddPatronDto patron){
        return patronService.addPatron(patron);
    }

    @PutMapping("/{id}")
    public String UpdatePatron(@PathVariable("id") Long id,@RequestBody UpdatePatronDto patron){
        patronService.updatePatron(id,patron);
        return "Patron updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String deletePatron(@PathVariable("id") Long id) throws Exception {
        patronService.deletePatron(id);
        return "Book deleted Successfully";
    }


}
