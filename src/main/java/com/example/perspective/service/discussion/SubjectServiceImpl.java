package com.example.perspective.service.discussion;

import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.Subject;
import com.example.perspective.model.mappers.SubjectMapper;
import com.example.perspective.repository.SubjectRepository;
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
public class SubjectServiceImpl implements SubjectService {

    private final static Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class.getName());

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Validates the name of a discussion subject
     * @param name of the expert
     */
    public void validateSubject(String name) throws InvalidDataException {
        logger.info("Validate subject details {}", name);
        new NameValidator().validate(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(SubjectMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subject findByName(String name) {
        Optional<Subject> subject = subjectRepository.findByName(name);

        return subject.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubjectDTO save(SubjectDTO subjectDTO) throws InvalidDataException, DuplicateNameException {
        validateSubject(subjectDTO.getName());

        Subject _subject = this.findByName(subjectDTO.getName());
        if (_subject != null) {
            logger.error("A discussion subject with the name {} already exists!", subjectDTO.getName());
            throw new DuplicateNameException("A discussion subject with the name " + subjectDTO.getName() + " already exists!");
        }

        Subject s = SubjectMapper.getInstance().convertFromDTO(subjectDTO);
        Subject savedSubject = subjectRepository.save(s);

        return SubjectMapper.getInstance().convertToDTO(savedSubject);
    }

}
