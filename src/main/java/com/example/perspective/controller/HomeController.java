package com.example.perspective.controller;

import com.example.perspective.model.DTO.ResourceDTO;
import com.example.perspective.model.DTO.ResourceResponseDTO;
import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.Resource;
import com.example.perspective.model.mappers.ResourceMapper;
import com.example.perspective.service.account.expert.ExpertUserServiceImpl;
import com.example.perspective.service.discussion.SubjectServiceImpl;
import com.example.perspective.service.discussion.resource.ResourceServiceImpl;
import com.example.perspective.service.discussion.topic.TopicServiceImpl;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class.getName());

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private TopicServiceImpl topicService;

    @GetMapping("/getSubjects")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getTopics")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<TopicDTO>> getTopics() {
        return new ResponseEntity<>(topicService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<byte[]> getResource(@PathVariable String id) {
        Resource resource = resourceService.getResource(Integer.valueOf(id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getName() + "\"")
                .body(resource.getData());
    }

    @GetMapping("/getResource")
    public ResponseEntity<ResourceDTO> getResources(@Param("resourceName") String resourceName) {
        logger.info("Get resource {} from the database.", resourceName);
        return new ResponseEntity<>(
                ResourceMapper.getInstance().convertToDTO(resourceService.findByName(resourceName)),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/getResources")
    public ResponseEntity<List<ResourceResponseDTO>> getResources() {
        logger.info("Get all resources from the database.");
        return new ResponseEntity<>(
                resourceService.findAll(),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/getSubjectTopics")
    public ResponseEntity<List<TopicDTO>> getTopicsForSubject(@Param("subjectName") String subjectName) throws InvalidDataException {
        logger.info("Get all discussion topics for the subject {}.", subjectName);
        return new ResponseEntity<>(
                topicService.findAllBySubjectName(subjectName),
                HttpStatus.ACCEPTED
        );
    }
}
