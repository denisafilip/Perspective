package com.example.perspective.service.subject;

import com.example.perspective.model.Subject;
import com.example.perspective.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subject findByName(String name) {
        Optional<Subject> subject = subjectRepository.findByName(name);

        return subject.orElse(null);
    }

}
