package com.example.perspective.controller;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.DTO.ResourceDTO;
import com.example.perspective.model.DTO.ResourceResponseDTO;
import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.mappers.ExpertMapper;
import com.example.perspective.model.mappers.ResourceResponseMapper;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.account.expert.ExpertUserServiceImpl;
import com.example.perspective.service.discussion.resource.ResourceServiceImpl;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private ResourceServiceImpl resourceService;

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
    public ResponseEntity<List<SubjectDTO>> getSubjectsOfExpert(@Param("expertEmail") String expertEmail) {
        logger.info("Obtain logged in expert user data.");
        return new ResponseEntity<>(expertUserService.getSubjects(expertEmail), HttpStatus.ACCEPTED);
    }

    @PostMapping("/uploadResource")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<ResourceResponseDTO> uploadResource(@RequestPart(value = "topicName", required = false) String topicName,
                                                      @RequestPart(value = "expertDTO", required = false) ExpertDTO expertDTO,
                                                      @RequestPart("file") MultipartFile file) throws InvalidDataException, IOException {
        logger.info("Uploading a new resource to the database...");
        return new ResponseEntity<>(resourceService.save(topicName, expertDTO, file), HttpStatus.CREATED);
    }

    @GetMapping("/getResourcesByExpert")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<List<ResourceResponseDTO>> getResources(@Param("expertEmail") String expertEmail) throws InvalidDataException {
        logger.info("Get all resources from the database that were added by the expert with the email {}.", expertEmail);
        return new ResponseEntity<>(
                resourceService.findAllByExpertEmail(expertEmail),
                HttpStatus.ACCEPTED
        );
    }

}
