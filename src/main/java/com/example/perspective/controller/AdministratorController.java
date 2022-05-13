package com.example.perspective.controller;

import com.example.perspective.model.DTO.AdministratorDTO;
import com.example.perspective.model.mappers.AdministratorMapper;
import com.example.perspective.service.account.administrator.AdministratorServiceImpl;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that handles the operations of an administrator.
 */
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    private final static Logger logger = LoggerFactory.getLogger(AdministratorController.class.getName());

    @Autowired
    private AdministratorServiceImpl administratorService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<AdministratorDTO> getAdministrators() {
        return administratorService.findAll();
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AdministratorDTO getCurrentAdministrator(@Param("adminEmail") String adminEmail) {
        logger.info("Obtain logged in administrator data.");
        return AdministratorMapper.getInstance().convertToDTO(administratorService.findByEmail(adminEmail));
    }

    @PostMapping("/register")
    public ResponseEntity<AdministratorDTO> save(@Validated @RequestBody AdministratorDTO administratorDTO) throws InvalidDataException {
        logger.info("Save administratorDTO to database");
        return new ResponseEntity<>(administratorService.register(administratorDTO), HttpStatus.CREATED);
    }

}
