package com.example.perspective.service.account;

import com.example.perspective.model.Account;
import com.example.perspective.model.Administrator;
import com.example.perspective.model.DTO.AccountDTO;
import com.example.perspective.model.DTO.LoginDTO;
import com.example.perspective.model.Expert;
import com.example.perspective.model.User;
import com.example.perspective.model.mappers.AccountMapper;
import com.example.perspective.model.mappers.AdministratorMapper;
import com.example.perspective.model.mappers.ExpertMapper;
import com.example.perspective.model.mappers.UserMapper;
import com.example.perspective.repository.AdministratorRepository;
import com.example.perspective.repository.ExpertRepository;
import com.example.perspective.repository.UserRepository;
import com.example.perspective.service.account.exceptions.NoSuchAccountException;
import com.example.perspective.service.exceptions.InvalidDataException;
import com.example.perspective.service.validators.NameValidator;
import com.example.perspective.service.validators.UserEmailValidator;
import com.example.perspective.service.validators.UserPasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class that implements the methods for the user account management
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class.getName());

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpertRepository expertRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDTO findByEmail(String email) {
        logger.info("Get account with email {}", email);
        Optional<Administrator> administrator = administratorRepository.findByEmail(email);
        if (administrator.isPresent()) {
            return administrator.map(value -> AdministratorMapper.getInstance().convertToDTO(value)).orElse(null);
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.map(value -> UserMapper.getInstance().convertToDTO(value)).orElse(null);
        }
        Optional<Expert> expert = expertRepository.findByEmail(email);
        if (expert.isPresent()) {
            return expert.map(value -> ExpertMapper.getInstance().convertToDTO(value)).orElse(null);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDTO getAccountDTO(LoginDTO loginDTO) throws NoSuchAccountException {
        AccountDTO accountDTO = this.findByEmail(loginDTO.getEmail());
        if (accountDTO == null) {
            logger.warn("No account with email {} was found", loginDTO.getEmail());
            throw new NoSuchAccountException("No account with this email was found.");
        }
        Account account = AccountMapper.getInstance().convertFromDTO(accountDTO);
        if (BCrypt.checkpw(loginDTO.getPassword(), account.getPassword())) {
            return accountDTO;
        }
        logger.warn("The password for the email {} is incorrect!", loginDTO.getEmail());
        throw new NoSuchAccountException("The password for this email is incorrect!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDTO logIn(LoginDTO loginDTO) throws NoSuchAccountException {
        return this.getAccountDTO(loginDTO);
    }

}
