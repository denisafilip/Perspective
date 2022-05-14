package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.Topic;

public class TopicMapper implements Mapper<Topic, TopicDTO> {

    /**
     * Singleton instance of the TopicMapper class.
     */
    private static TopicMapper topicMapper = null;

    /**
     * Constructor.
     */
    private TopicMapper() {

    }

    /**
     * Retrieves the single instance of the TopicMapper class.
     * @return the instance of the TopicMapper class.
     */
    public static TopicMapper getInstance() {
        if (topicMapper == null) {
            topicMapper = new TopicMapper();
        }
        return topicMapper;
    }

    @Override
    public Topic convertFromDTO(TopicDTO topicDTO) {
        return Topic.builder()
                .name(topicDTO.getName())
                .description(topicDTO.getDescription())
                .subject(SubjectMapper.getInstance().convertFromDTO(topicDTO.getSubjectDTO()))
                .build();
    }

    @Override
    public TopicDTO convertToDTO(Topic topic) {
        return TopicDTO.builder()
                .name(topic.getName())
                .description(topic.getDescription())
                .subjectDTO(SubjectMapper.getInstance().convertToDTO(topic.getSubject()))
                .build();
    }
}
