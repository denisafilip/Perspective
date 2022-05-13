package com.example.perspective.service.subject;

import com.example.perspective.model.Subject;

import java.util.List;

public interface SubjectService {

    /**
     * Retrieves all discussion subjects from the database.
     * @return Retrieved list of discussion subjects.
     */
    List<Subject> findAll();

    /**
     * Retrieves a discussion subject from the database by its name.
     * @param name Name of the subject we search for.
     * @return The found discussion subject.
     */
    Subject findByName(String name);

}
