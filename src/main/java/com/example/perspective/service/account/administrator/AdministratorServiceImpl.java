package com.example.perspective.service.account.administrator;

import com.example.perspective.model.Administrator;
import com.example.perspective.model.DTO.AdministratorDTO;
import com.example.perspective.model.mappers.AdministratorMapper;
import com.example.perspective.repository.AdministratorRepository;
import com.example.perspective.service.exceptions.InvalidDataException;
import com.example.perspective.service.validators.NameValidator;
import com.example.perspective.service.validators.UserEmailValidator;
import com.example.perspective.service.validators.UserPasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final static Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class.getName());

    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * Validates the details of an account
     * @param username of the account
     * @param firstName of the account
     * @param lastName of the account
     * @param email of the account
     * @param password of the account
     */
    public void validateAdmin(String username, String firstName, String lastName, String email, String password) throws InvalidDataException {
        logger.info("Validate account details {}, {}, {}, {}", username, firstName, lastName, email);
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
    public AdministratorDTO register(AdministratorDTO administratorDTO) throws InvalidDataException {
        validateAdmin(administratorDTO.getUsername(),
                administratorDTO.getFirstName(),
                administratorDTO.getLastName(),
                administratorDTO.getEmail(),
                administratorDTO.getPassword());

        Administrator a = Administrator.AdminBuilder()
                .username(administratorDTO.getUsername())
                .email(administratorDTO.getEmail())
                .firstName(administratorDTO.getFirstName())
                .lastName(administratorDTO.getLastName())
                .password(BCrypt.hashpw(administratorDTO.getPassword(), BCrypt.gensalt(12)))
                .build();
        Administrator admin = administratorRepository.save(a);
        return AdministratorMapper.getInstance().convertToDTO(admin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdministratorDTO> findAll() {
        logger.info("Retrieve all administrators from the database");
        return administratorRepository.findAll().stream()
                .map(AdministratorMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Administrator findByEmail(String email) {
        Optional<Administrator> administrator = administratorRepository.findByEmail(email);

        return administrator.orElse(null);
    }
}
