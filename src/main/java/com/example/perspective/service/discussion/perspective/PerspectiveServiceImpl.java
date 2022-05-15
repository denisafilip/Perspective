package com.example.perspective.service.discussion.perspective;

import com.example.perspective.model.DTO.PerspectiveDTO;
import com.example.perspective.model.Perspective;
import com.example.perspective.model.Spectrum;
import com.example.perspective.model.Topic;
import com.example.perspective.model.User;
import com.example.perspective.model.mappers.PerspectiveMapper;
import com.example.perspective.model.mappers.TopicMapper;
import com.example.perspective.model.mappers.UserMapper;
import com.example.perspective.repository.PerspectiveRepository;
import com.example.perspective.service.account.exceptions.NoSuchAccountException;
import com.example.perspective.service.account.user.UserServiceImpl;
import com.example.perspective.service.discussion.topic.TopicServiceImpl;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerspectiveServiceImpl implements PerspectiveService {

    private final static Logger logger = LoggerFactory.getLogger(PerspectiveServiceImpl.class.getName());

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private PerspectiveRepository perspectiveRepository;

    public Perspective findByUserAndTopic(User u, Topic t) {
        Optional<Perspective> perspective = perspectiveRepository.findByUserAndTopic(u, t);

        return perspective.orElse(null);
    }

    @Override
    public PerspectiveDTO save(PerspectiveDTO perspectiveDTO) throws InvalidDataException, DuplicateNameException {
        User u = userService.findByEmail(perspectiveDTO.getUserDTO().getEmail());

        if (u == null) {
            logger.error("No user with the email {} was found!", perspectiveDTO.getUserDTO().getEmail());
            throw new NoSuchAccountException("No user with the email " + perspectiveDTO.getUserDTO().getEmail() + " was found!");
        }

        Topic t = topicService.findByName(perspectiveDTO.getTopicDTO().getName());

        if (t == null) {
            logger.error("No topic with the name {} was found!", perspectiveDTO.getTopicDTO().getName());
            throw new InvalidDataException("No topic with the name " + perspectiveDTO.getTopicDTO().getName() + " was found!");
        }

        Perspective p = findByUserAndTopic(u, t);

        if (p != null) {
            logger.error("A perspective was already created for topic {} and user {}!", t, u);
            throw new DuplicateNameException("A perspective was already created for topic " + t + " and user " + u + "!");
        }

        Perspective perspective = Perspective.builder()
                .belief(Spectrum.UNDEFINED)
                .notes("")
                .topic(t)
                .user(u)
                .build();

        Perspective savedPerspective = perspectiveRepository.save(perspective);
        return PerspectiveMapper.getInstance().convertToDTO(savedPerspective);
    }

    @Override
    public PerspectiveDTO update(PerspectiveDTO perspectiveDTO) throws InvalidDataException, DuplicateNameException {
        User u = userService.findByEmail(perspectiveDTO.getUserDTO().getEmail());

        if (u == null) {
            logger.error("No user with the email {} was found!", perspectiveDTO.getUserDTO().getEmail());
            throw new NoSuchAccountException("No user with the email " + perspectiveDTO.getUserDTO().getEmail() + " was found!");
        }

        Topic t = topicService.findByName(perspectiveDTO.getTopicDTO().getName());

        if (t == null) {
            logger.error("No topic with the name {} was found!", perspectiveDTO.getTopicDTO().getName());
            throw new InvalidDataException("No topic with the name " + perspectiveDTO.getTopicDTO().getName() + " was found!");
        }

        Perspective p = findByUserAndTopic(u, t);

        if (p == null) {
            logger.error("No perspective was found for topic {} and user {}!", t, u);
            throw new DuplicateNameException("A perspective was already created for topic " + t + " and user " + u + "!");
        }

        p.setBelief(perspectiveDTO.getBelief());
        p.setNotes(perspectiveDTO.getNotes());

        Perspective savedPerspective = perspectiveRepository.save(p);
        return PerspectiveMapper.getInstance().convertToDTO(savedPerspective);
    }
}
