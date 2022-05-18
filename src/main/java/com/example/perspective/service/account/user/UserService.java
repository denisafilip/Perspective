package com.example.perspective.service.account.user;

import com.example.perspective.model.DTO.UserDTO;
import com.example.perspective.model.User;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.exceptions.InvalidDataException;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    /**
     * Saves a User instance to the database
     * @param user saved in the database
     * @return the newly registered User
     */
    @Transactional
    User save(User user);

    /**
     * Retrieves all registered Users on the food delivery platform
     * @return all registered Users on the platform
     */
    List<UserDTO> findAll();

    /**
     * Retrieves a User from the database by their email
     * @param email of the User
     * @return the retrieved User instance
     */
    User findByEmail(String email);

    /**
     * Retrieves a User from the database by their username
     * @param username of the User
     * @return the retrieved User instance
     */
    User findByUsername(String username);

    /**
     * Registers a User on the food delivery platform
     * @param UserDTO, containing the details of the User to be registered
     * @return the registered User
     * @throws InvalidDataException if the details of the User are invalid
     * @throws DuplicateEmailException if an account with the same email exists already
     */
    UserDTO register(UserDTO UserDTO) throws InvalidDataException, DuplicateEmailException;
    
}
