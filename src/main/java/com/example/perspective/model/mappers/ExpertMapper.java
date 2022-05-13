package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.Expert;

public class ExpertMapper implements Mapper<Expert, ExpertDTO> {

    /**
     * Singleton instance of the ExpertMapper class.
     */
    private static ExpertMapper expertMapper = null;

    /**
     * Constructor.
     */
    private ExpertMapper() {

    }

    /**
     * Retrieves the single instance of the ExpertMapper class.
     * @return the instance of the ExpertMapper class.
     */
    public static ExpertMapper getInstance() {
        if (expertMapper == null) {
            expertMapper = new ExpertMapper();
        }
        return expertMapper;
    }

    @Override
    public Expert convertFromDTO(ExpertDTO expertDTO) {
        return null;
    }

    @Override
    public ExpertDTO convertToDTO(Expert expert) {
        return ExpertDTO.ExpertDTOBuilder()
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .email(expert.getEmail())
                .username(expert.getUsername())
                .password(expert.getPassword())
                .subjects(expert.getSubjects())
                .build();
    }
}
