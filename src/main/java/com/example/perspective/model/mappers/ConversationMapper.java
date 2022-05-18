package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.ConversationDTO;
import com.example.perspective.model.Conversation;

import java.io.IOException;

public class ConversationMapper implements Mapper<Conversation, ConversationDTO> {

    /**
     * Singleton instance of the ConversationMapper class.
     */
    private static ConversationMapper conversationMapper = null;

    /**
     * Constructor.
     */
    private ConversationMapper() {

    }

    /**
     * Retrieves the single instance of the ConversationMapper class.
     * @return the instance of the ConversationMapper class.
     */
    public static ConversationMapper getInstance() {
        if (conversationMapper == null) {
            conversationMapper = new ConversationMapper();
        }
        return conversationMapper;
    }

    @Override
    public Conversation convertFromDTO(ConversationDTO conversationDTO) throws IOException {
        return null;
    }

    @Override
    public ConversationDTO convertToDTO(Conversation message) {
        return ConversationDTO.builder()
                .receiver(UserMapper.getInstance().convertToDTO(message.getReceiver()))
                .sender(UserMapper.getInstance().convertToDTO(message.getSender()))
                .topicDTO(TopicMapper.getInstance().convertToDTO(message.getTopic()))
                .message(message.getMessage())
                .build();
    }
}
