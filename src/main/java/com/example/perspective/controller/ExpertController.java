package com.example.perspective.controller;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.mappers.ExpertMapper;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.account.expert.ExpertUserServiceImpl;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that handles the operations of an expert.
 */
@RestController
@RequestMapping("/expert")
public class ExpertController {

    private final static Logger logger = LoggerFactory.getLogger(ExpertController.class.getName());

    @Autowired
    private ExpertUserServiceImpl expertUserService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('EXPERT')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ExpertDTO> getExperts() {
        return expertUserService.findAll();
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('EXPERT')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ExpertDTO getCurrentExpert(@Param("expertEmail") String expertEmail) {
        logger.info("Obtain logged in expert user data.");
        return ExpertMapper.getInstance().convertToDTO(expertUserService.findByEmail(expertEmail));
    }

    @PostMapping("/register")
    public ResponseEntity<ExpertDTO> save(@Validated @RequestBody(required = false) ExpertDTO expertDTO) throws InvalidDataException, DuplicateEmailException {
        logger.info("Save expertDTO to database");
        System.out.println(expertDTO);
        return new ResponseEntity<>(expertUserService.register(expertDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getSubjects")
    @PreAuthorize("hasRole('EXPERT')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<SubjectDTO>> getSubjectsOfExpert(@Param("expertEmail") String expertEmail) {
        logger.info("Obtain logged in expert user data.");
        return new ResponseEntity<>(expertUserService.getSubjects(expertEmail), HttpStatus.ACCEPTED);
    }
}
