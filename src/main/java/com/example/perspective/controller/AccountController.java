package com.example.perspective.controller;

import com.example.perspective.model.AccountRole;
import com.example.perspective.model.DTO.*;
import com.example.perspective.service.account.AccountServiceImpl;
import com.example.perspective.service.account.exceptions.InvalidPasswordException;
import com.example.perspective.service.security.JwtResponse;
import com.example.perspective.service.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class.getName());

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Logins an account on the platform
     * @param loginDTO - credentials of the current account
     * @return response received after account log in
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody(required = false) LoginDTO loginDTO) throws InvalidPasswordException {
        String jwt = jwtUtils.generateJwtToken(loginDTO.getEmail());

        AccountDTO accountDTO = accountService.logIn(loginDTO);
        
        String role = null;
        if (accountDTO instanceof AdministratorDTO) {
            role = AccountRole.ROLE_ADMIN.toString();
        } else if (accountDTO instanceof UserDTO) {
             role = AccountRole.ROLE_USER.toString();
        } else if (accountDTO instanceof ExpertDTO) {
            role = AccountRole.ROLE_EXPERT.toString();
        }

        logger.info("Generated JWT token {}", jwt);
        return new ResponseEntity<>(new JwtResponse(jwt, accountDTO.getEmail(), role), HttpStatus.OK);
    }
}
