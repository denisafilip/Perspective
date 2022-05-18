package com.example.perspective;

import com.example.perspective.model.DTO.AccountDTO;
import com.example.perspective.model.DTO.AdministratorDTO;
import com.example.perspective.model.*;
import com.example.perspective.model.DTO.*;
import com.example.perspective.model.mappers.SubjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DummyTestObjects {

    public AccountDTO createTestAccountDTO() {
        return AccountDTO.builder()
                .email("test@gmail.com")
                .username("testtest")
                .firstName("Andreea")
                .lastName("Ionescu")
                .password("Password_123")
                .build();
    }

    public AdministratorDTO createTestAdministratorDTO() {
        AccountDTO accountDTO = createTestAccountDTO();
        return AdministratorDTO.AdministratorDTOBuilder()
                .email(accountDTO.getEmail())
                .username(accountDTO.getUsername())
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .password(accountDTO.getPassword())
                .build();
    }

    public Administrator createTestAdministrator() {
        AccountDTO accountDTO = createTestAccountDTO();
        Administrator admin = Administrator.AdminBuilder()
                .email(accountDTO.getEmail())
                .username(accountDTO.getUsername())
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .password(accountDTO.getPassword())
                .build();
        admin.setIdUser(1);
        return admin;
    }

    public User createTestUser() {
        AccountDTO accountDTO = createTestAccountDTO();
        User user = User.UserBuilder()
                .email(accountDTO.getEmail())
                .username(accountDTO.getUsername())
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .password(accountDTO.getPassword())
                .build();
        user.setIdUser(1);
        return user;
    }

    public UserDTO createTestUserDTO() {
        AccountDTO accountDTO = createTestAccountDTO();
        return UserDTO.UserDTOBuilder()
                .email(accountDTO.getEmail())
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .password(accountDTO.getPassword())
                .username(accountDTO.getUsername())
                .build();
    }

    public Subject createTestSubject() {
        return Subject.builder()
                .idSubject(1)
                .description("Description")
                .name("Test")
                .build();
    }

    public SubjectDTO createTestSubjectDTO() {
        return SubjectMapper.getInstance().convertToDTO(createTestSubject());
    }

    public Topic createTestTopic() {
        return Topic.builder()
                .subject(createTestSubject())
                .name("TestTopic")
                .description("TestTopicDescription")
                .build();
    }
}


