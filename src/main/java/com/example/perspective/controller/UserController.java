package com.example.perspective.controller;

import com.example.perspective.model.DTO.PerspectiveDTO;
import com.example.perspective.model.DTO.UserDTO;
import com.example.perspective.model.mappers.UserMapper;
import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.account.user.UserServiceImpl;
import com.example.perspective.service.discussion.perspective.PerspectiveServiceImpl;
import com.example.perspective.service.discussion.topic.TopicServiceImpl;
import com.example.perspective.service.exceptions.DuplicateNameException;
import com.example.perspective.service.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PerspectiveServiceImpl perspectiveService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerCustomer(@RequestBody(required = false) UserDTO userDTO)
            throws InvalidDataException, DuplicateEmailException {
        return new ResponseEntity<>(userService.register(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserDTO> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDTO getCurrentCustomer(@Param("userEmail") String userEmail) {
        return UserMapper.getInstance().convertToDTO(userService.findByEmail(userEmail));
    }

    @GetMapping("/getByUsername")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDTO> getCurrentUserByUsername(@Param("username") String username) {
        return new ResponseEntity<>(UserMapper.getInstance().convertToDTO(userService.findByUsername(username)),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/getPerspectives")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<PerspectiveDTO>> getPerspectives(@Param("userEmail") String userEmail) {
        return new ResponseEntity<>(userService.getPerspectives(userEmail), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addPerspective")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PerspectiveDTO> markTopic(@RequestBody(required = false) PerspectiveDTO perspectiveDTO)
            throws InvalidDataException, DuplicateNameException {
        return new ResponseEntity<>(perspectiveService.save(perspectiveDTO), HttpStatus.CREATED);
    }

    @PostMapping("/modifyPerspective")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PerspectiveDTO> modifyPerspective(@RequestBody(required = false) PerspectiveDTO perspectiveDTO)
            throws InvalidDataException, DuplicateNameException {
        return new ResponseEntity<>(perspectiveService.update(perspectiveDTO), HttpStatus.CREATED);
    }
}
