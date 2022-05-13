package com.example.perspective.controller;

import com.example.perspective.model.DTO.UserDTO;
import com.example.perspective.model.mappers.UserMapper;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.account.user.UserServiceImpl;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerCustomer(@RequestBody(required = false) UserDTO userDTO)
            throws InvalidDataException, DuplicateEmailException {
        return new ResponseEntity<>(userService.register(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDTO getCurrentCustomer(@Param("userEmail") String userEmail) {
        return UserMapper.getInstance().convertToDTO(userService.findByEmail(userEmail));
    }
}
