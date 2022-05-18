package com.example.perspective.service.discussion.conversation;

import com.example.perspective.model.Conversation;
import com.example.perspective.model.DTO.ConversationDTO;
import com.example.perspective.model.Topic;
import com.example.perspective.model.User;
import com.example.perspective.model.mappers.ConversationMapper;
import com.example.perspective.model.mappers.SubjectMapper;
import com.example.perspective.repository.ConversationRepository;
import com.example.perspective.repository.PerspectiveRepository;
import com.example.perspective.service.account.exceptions.NoSuchAccountException;
import com.example.perspective.service.account.user.UserServiceImpl;
import com.example.perspective.service.discussion.perspective.PerspectiveServiceImpl;
import com.example.perspective.service.discussion.topic.TopicServiceImpl;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final static Logger logger = LoggerFactory.getLogger(ConversationServiceImpl.class.getName());

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public ConversationDTO save(ConversationDTO conversationDTO) throws InvalidDataException {
        User receiver = userService.findByUsername(conversationDTO.getReceiver().getUsername());

        if (receiver == null) {
            logger.error("No user with the username {} was found!", conversationDTO.getReceiver().getUsername());
            throw new NoSuchAccountException("No user with the username " + conversationDTO.getReceiver().getUsername() + " was found!");
        }

        User sender = userService.findByUsername(conversationDTO.getSender().getUsername());

        if (sender == null) {
            logger.error("No user with the username {} was found!", conversationDTO.getSender().getUsername());
            throw new NoSuchAccountException("No user with the username " + conversationDTO.getSender().getUsername() + " was found!");
        }

        Topic t = topicService.findByName(conversationDTO.getTopicDTO().getName());

        if (t == null) {
            logger.error("No topic with the name {} was found!", conversationDTO.getTopicDTO().getName());
            throw new InvalidDataException("No topic with the name " + conversationDTO.getTopicDTO().getName() + " was found!");
        }

        Conversation conversation = Conversation.builder()
                .receiver(receiver)
                .sender(sender)
                .message(conversationDTO.getMessage())
                .topic(t)
                .build();
        return ConversationMapper.getInstance().convertToDTO(conversationRepository.save(conversation));
    }

    @Override
    public List<ConversationDTO> findAllMessagesInConversation(String receiverUsername, String senderUsername, String topicName) throws InvalidDataException {
        User receiver = userService.findByUsername(receiverUsername);

        if (receiver == null) {
            logger.error("No user with the username {} was found!", receiverUsername);
            throw new NoSuchAccountException("No user with the username " + receiverUsername + " was found!");
        }

        User sender = userService.findByUsername(senderUsername);

        if (sender == null) {
            logger.error("No user with the username {} was found!", senderUsername);
            throw new NoSuchAccountException("No user with the username " + senderUsername + " was found!");
        }

        Topic topic = topicService.findByName(topicName);

        if (topic == null) {
            logger.error("No topic with the name {} was found!", topicName);
            throw new InvalidDataException("No topic with the name " + topicName + " was found!");
        }

        List<Conversation> allMessages = Stream.concat(
                conversationRepository.findAllBySenderAndReceiverAndTopic(sender, receiver, topic).stream(),
                conversationRepository.findAllBySenderAndReceiverAndTopic(receiver, sender, topic).stream()).
                sorted().collect(Collectors.toList());

        return allMessages.stream()
                .map(ConversationMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());

    }

}
