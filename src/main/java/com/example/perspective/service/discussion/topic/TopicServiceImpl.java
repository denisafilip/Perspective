package com.example.perspective.service.discussion.topic;

import com.example.perspective.model.DTO.TopicDTO;
import com.example.perspective.model.Subject;
import com.example.perspective.model.Topic;
import com.example.perspective.model.mappers.TopicMapper;
import com.example.perspective.repository.TopicRepository;
import com.example.perspective.service.discussion.SubjectService;
import com.example.perspective.service.discussion.SubjectServiceImpl;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;
import com.example.perspective.service.validators.NameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private final static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class.getName());

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SubjectServiceImpl subjectService;

    /**
     * Validates the name of a discussion topic
     * @param name of the expert
     */
    public void validateTopic(String name) throws InvalidDataException {
        logger.info("Validate topic details {}", name);
        new NameValidator().validate(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopicDTO> findAll() {
        return topicRepository.findAll().stream()
                .map(TopicMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Topic findByName(String name) {
        Optional<Topic> topic = topicRepository.findByName(name);

        return topic.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopicDTO save(TopicDTO topicDTO) throws InvalidDataException, DuplicateNameException {
        System.out.println(topicDTO);
        validateTopic(topicDTO.getName());

        Topic _topic = this.findByName(topicDTO.getName());
        if (_topic != null) {
            logger.error("A discussion topic with the name {} already exists!", topicDTO.getName());
            throw new DuplicateNameException("A discussion topic with the name " + topicDTO.getName() + " already exists!");
        }

        Subject subject = subjectService.findByName(topicDTO.getSubjectDTO().getName());

        if (subject == null) {
            logger.error("No discussion subject with the name {} was found!", topicDTO.getSubjectDTO().getName());
            throw new InvalidDataException("No discussion subject with the name " + topicDTO.getSubjectDTO().getName() + " was found!");
        }

        Topic t = TopicMapper.getInstance().convertFromDTO(topicDTO);
        t.setSubject(subject);
        Topic savedTopic = topicRepository.save(t);

        return TopicMapper.getInstance().convertToDTO(savedTopic);
    }

}
