package com.example.perspective.controller;

import com.example.perspective.model.DTO.ConversationDTO;
import com.example.perspective.model.Conversation;
import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.DTO.UserDTO;
import com.example.perspective.model.mappers.TopicMapper;
import com.example.perspective.service.discussion.conversation.ConversationServiceImpl;
import com.example.perspective.service.discussion.topic.TopicService;
import com.example.perspective.service.discussion.topic.TopicServiceImpl;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    private ConversationServiceImpl conversationService;

    @Autowired
    private TopicServiceImpl topicService;

    @PostMapping("/sendMessage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ConversationDTO> sendMessage(@Validated @RequestBody(required = false) ConversationDTO conversationDTO) throws InvalidDataException {
        return new ResponseEntity<>(conversationService.save(conversationDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ConversationDTO>> getMessagesInConversation(@Param("receiverUsername") String receiverUsername,
                                                           @Param("senderUsername") String senderUsername,
                                                           @Param("topicName") String topicName) throws InvalidDataException {
        return new ResponseEntity<>(conversationService.findAllMessagesInConversation(receiverUsername, senderUsername, topicName),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/getConversationTopic")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TopicDTO> getConversationTopic(@Param("topicName") String topicName) throws InvalidDataException {
        return new ResponseEntity<>(TopicMapper.getInstance().convertToDTO(topicService.findByName(topicName)), HttpStatus.ACCEPTED);
    }
}