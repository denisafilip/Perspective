package com.example.perspective.service.discussion.topic;

import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.Topic;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;

import java.util.List;

public interface TopicService {

    /**
     * Retrieves all discussion topics from the database.
     * @return Retrieved list of discussion subjects.
     */
    List<TopicDTO> findAll();

    /**
     * Retrieves a discussion topic from the database by its name.
     * @param name Name of the topic we search for.
     * @return The found discussion topic.
     */
    Topic findByName(String name);

    /**
     * Adds a new discussion topic instance to the database.
     * @param topicDTO Topic to be created.
     * @return The newly created discussion topic.
     */
    TopicDTO save(TopicDTO topicDTO) throws InvalidDataException, DuplicateNameException;

}
