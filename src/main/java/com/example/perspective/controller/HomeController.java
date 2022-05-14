package com.example.perspective.controller;

import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.service.discussion.SubjectServiceImpl;
import com.example.perspective.service.discussion.topic.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

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
}
