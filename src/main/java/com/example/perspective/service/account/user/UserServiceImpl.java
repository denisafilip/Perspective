package com.example.perspective.service.account.user;

import com.example.perspective.model.DTO.UserDTO;
import com.example.perspective.model.User;
import com.example.perspective.model.mappers.UserMapper;
import com.example.perspective.repository.UserRepository;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.exceptions.InvalidDataException;
import com.example.perspective.service.validators.NameValidator;
import com.example.perspective.service.validators.UserEmailValidator;
import com.example.perspective.service.validators.UserPasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    /**
     * Validates the details of a user
     * @param username of the user
     * @param firstName of the user
     * @param lastName of the user
     * @param email of the user
     * @param password of the user
     */
    public void validateUser(String username, String firstName, String lastName, String email, String password) throws InvalidDataException {
        logger.info("Validate user details {}, {}, {}, {}", username, firstName, lastName, email);
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
    public User save(User user) {
        logger.info("Save user {} {}, with email {} and username {}", user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername());
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> findAll() {
        logger.info("Get all customers from the database");
        return userRepository.findAll().stream()
                .map(UserMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO register(UserDTO userDTO) throws InvalidDataException, DuplicateEmailException {
        logger.info("Register user {} {}, with email {}, to database", userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getEmail());
        validateUser(userDTO.getUsername(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getPassword());

        Optional<User> _user = userRepository.findByEmailOrUsername(userDTO.getEmail(), userDTO.getUsername());

        if (_user.isPresent()) {
            logger.error("An user with the email {} already exists!", userDTO.getEmail());
            throw new DuplicateEmailException("An user with that email already exists!");
        }

        User u = UserMapper.getInstance().convertFromDTO(userDTO);
        User savedUser = userRepository.save(u);
        return UserMapper.getInstance().convertToDTO(savedUser);
    }
}
