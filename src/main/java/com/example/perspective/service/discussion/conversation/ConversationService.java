package com.example.perspective.service.discussion.conversation;

import com.example.perspective.model.DTO.ConversationDTO;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;

import java.util.List;

public interface ConversationService {

    ConversationDTO save(ConversationDTO conversationDTO) throws InvalidDataException, DuplicateNameException;

    List<ConversationDTO> findAllMessagesInConversation(String receiverEmail, String senderEmail, String topicName) throws InvalidDataException;
}
