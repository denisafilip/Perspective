package com.example.perspective.service.account.administrator;

import com.example.perspective.model.Administrator;
import com.example.perspective.model.DTO.AdministratorDTO;
import com.example.perspective.service.exceptions.InvalidDataException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service interface for administrator operations.
 */
public interface AdministratorService {
    /**
     * Saves an administrator instance to the database
     * @param administratorDTO to be saved
     * @return the saved administrator instance
     */
    @Transactional
    AdministratorDTO register(AdministratorDTO administratorDTO) throws InvalidDataException;

    /**
     * Retrieves all restaurant administrators from the database
     * @return list containing all restaurant administrators
     */
    List<AdministratorDTO> findAll();

    /**
     * Retrieves an administrator from the database by email
     * @param email of the administrator
     * @return the found administrator
     */
    Administrator findByEmail(String email);
}

