package com.example.perspective.service.discussion;

import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.Subject;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;

import java.util.List;

public interface SubjectService {

    /**
     * Retrieves all discussion subjects from the database.
     * @return Retrieved list of discussion subjects.
     */
    List<SubjectDTO> findAll();

    /**
     * Retrieves a discussion subject from the database by its name.
     * @param name Name of the subject we search for.
     * @return The found discussion subject.
     */
    Subject findByName(String name);

    /**
     * Adds a new discussion subject instance to the database.
     * @param subjectDTO Subject to be created.
     * @return The newly created discussion subject.
     */
    SubjectDTO save(SubjectDTO subjectDTO) throws InvalidDataException, DuplicateNameException;

}
