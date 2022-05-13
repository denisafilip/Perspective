package com.example.perspective.service.account.expert;

import com.example.perspective.model.DTO.ExpertDTO;
import com.example.perspective.model.Expert;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.exceptions.InvalidDataException;

import javax.transaction.Transactional;
import java.util.List;

public interface ExpertUserService {

    /**
     * Saves a Expert instance to the database
     * @param Expert saved in the database
     * @return the newly registered Expert
     */
    @Transactional
    Expert save(Expert expert);

    /**
     * Retrieves all registered Experts on the food delivery platform
     * @return all registered Experts on the platform
     */
    List<ExpertDTO> findAll();

    /**
     * Retrieves a Expert from the database by their email
     * @param email of the Expert
     * @return the retrieved Expert instance
     */
    Expert findByEmail(String email);

    /**
     * Registers a Expert on the food delivery platform
     * @param ExpertDTO, containing the details of the Expert to be registered
     * @return the registered Expert
     * @throws InvalidDataException if the details of the Expert are invalid
     * @throws DuplicateEmailException if an account with the same email exists already
     */
    ExpertDTO register(ExpertDTO ExpertDTO) throws InvalidDataException, DuplicateEmailException;
    
}
