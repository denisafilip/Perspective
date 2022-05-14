package com.example.perspective.service.account.expert;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.Expert;
import com.example.perspective.model.Subject;
import com.example.perspective.model.mappers.ExpertMapper;
import com.example.perspective.repository.ExpertRepository;
import com.example.perspective.repository.SubjectRepository;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.exceptions.InvalidDataException;
import com.example.perspective.service.validators.NameValidator;
import com.example.perspective.service.validators.UserEmailValidator;
import com.example.perspective.service.validators.UserPasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpertUserServiceImpl implements ExpertUserService {
    private final static Logger logger = LoggerFactory.getLogger(ExpertUserServiceImpl.class.getName());

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Validates the details of an expert user
     * @param username of the expert
     * @param firstName of the expert
     * @param lastName of the expert
     * @param email of the expert
     * @param password of the expert
     */
    public void validateExpert(String username, String firstName, String lastName, String email, String password) throws InvalidDataException {
        logger.info("Validate expert details {}, {}, {}, {}", username, firstName, lastName, email);
        new NameValidator().validate(username);
        new NameValidator().validate(firstName);
        new NameValidator().validate(lastName);
        new UserEmailValidator().validate(email);
        new UserPasswordValidator().validate(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expert save(Expert expert) {
        logger.info("Save expert user {} {}, with email {} and username {}", expert.getFirstName(),
                expert.getLastName(), expert.getEmail(), expert.getUsername());
        return expertRepository.save(expert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ExpertDTO> findAll() {
        logger.info("Get all expert users from the database");
        return expertRepository.findAll().stream()
                .map(ExpertMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Expert findByEmail(String email) {
        Optional<Expert> expert = expertRepository.findByEmail(email);

        return expert.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExpertDTO register(ExpertDTO expertDTO) throws InvalidDataException, DuplicateEmailException {
        logger.info("Register expert user {} {}, with email {}, to database", expertDTO.getFirstName(), expertDTO.getLastName(),
                expertDTO.getEmail());
        System.out.println(expertDTO);
        validateExpert(expertDTO.getUsername(),
                expertDTO.getFirstName(),
                expertDTO.getLastName(),
                expertDTO.getEmail(),
                expertDTO.getPassword());

        Optional<Expert> _expert = expertRepository.findByEmailOrUsername(expertDTO.getEmail(), expertDTO.getUsername());

        List<Subject> subjects = new ArrayList<>();
        for (SubjectDTO subjectDTO : expertDTO.getSubjects()) {
            Optional<Subject> subject = subjectRepository.findByName(subjectDTO.getName());
            subject.ifPresent(subjects::add);
        }


        if (_expert.isPresent()) {
            logger.error("An expert with the email {} already exists!", expertDTO.getEmail());
            throw new DuplicateEmailException("An expert with that email already exists!");
        }

        Expert expert = Expert.ExpertBuilder()
                .email(expertDTO.getEmail())
                .firstName(expertDTO.getFirstName())
                .lastName(expertDTO.getLastName())
                .username(expertDTO.getUsername())
                .password(BCrypt.hashpw(expertDTO.getPassword(), BCrypt.gensalt(12)))
                .subjects(subjects)
                .build();

        Expert savedExpert = expertRepository.save(expert);
        return ExpertMapper.getInstance().convertToDTO(savedExpert);
    }
}
