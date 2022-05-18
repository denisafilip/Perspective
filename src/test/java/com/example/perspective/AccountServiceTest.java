package com.example.perspective;

import com.example.perspective.model.Administrator;
import com.example.perspective.model.DTO.AccountDTO;
import com.example.perspective.model.DTO.LoginDTO;
import com.example.perspective.model.User;
import com.example.perspective.repository.AdministratorRepository;
import com.example.perspective.repository.ExpertRepository;
import com.example.perspective.repository.UserRepository;
import com.example.perspective.service.account.AccountServiceImpl;
import com.example.perspective.service.account.exceptions.InvalidPasswordException;
import com.example.perspective.service.account.exceptions.NoSuchAccountException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AdministratorRepository administratorRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExpertRepository expertRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private final DummyTestObjects dummy = new DummyTestObjects();

    @Test
    public void testFindByEmailAdministrator() {
        Administrator administrator = dummy.createTestAdministrator();

        Mockito.when(administratorRepository.findByEmail(administrator.getEmail())).thenReturn(Optional.of(administrator));

        AccountDTO foundAccountDTO = accountService.findByEmail(administrator.getEmail());

        Assertions.assertEquals(administrator.getEmail(), foundAccountDTO.getEmail());

    }

    @Test
    public void testFindByEmailUser() {
        User customer = dummy.createTestUser();

        Mockito.when(administratorRepository.findByEmail(customer.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        AccountDTO foundAccountDTO = accountService.findByEmail(customer.getEmail());

        Assertions.assertEquals(customer.getEmail(), foundAccountDTO.getEmail());
    }

    @Test(expected = NoSuchAccountException.class)
    public void testLoginNoAccount() throws InvalidPasswordException {
        Administrator administrator = dummy.createTestAdministrator();

        Mockito.when(administratorRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(expertRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        accountService.logIn(new LoginDTO(administrator.getEmail(), administrator.getPassword()));
    }
}
