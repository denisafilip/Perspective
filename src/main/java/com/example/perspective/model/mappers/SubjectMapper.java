package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.SubjectDTO;
import com.example.perspective.model.Subject;

public class SubjectMapper implements Mapper<Subject, SubjectDTO> {

    /**
     * Singleton instance of the SubjectMapper class.
     */
    private static SubjectMapper subjectMapper = null;

    /**
     * Constructor.
     */
    private SubjectMapper() {

    }

    /**
     * Retrieves the single instance of the SubjectMapper class.
     * @return the instance of the SubjectMapper class.
     */
    public static SubjectMapper getInstance() {
        if (subjectMapper == null) {
            subjectMapper = new SubjectMapper();
        }
        return subjectMapper;
    }

    @Override
    public Subject convertFromDTO(SubjectDTO subjectDTO) {
        return null;
    }

    @Override
    public SubjectDTO convertToDTO(Subject subject) {
        return SubjectDTO.builder()
                .name(subject.getName())
                .build();
    }
}
