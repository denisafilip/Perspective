package com.example.perspective.model.mappers;

import com.example.perspective.model.Account;
import com.example.perspective.model.DTO.AccountDTO;

/**
 * Singleton class used for mapping an Account to an AccountDTO and vice-versa.
 */
public class AccountMapper implements Mapper<Account, AccountDTO> {

    /**
     * Singleton instance of the AccountMapper class.
     */
    private static AccountMapper accountMapper = null;

    /**
     * Constructor.
     */
    private AccountMapper() {

    }

    /**
     * Retrieves the single instance of the AccountMapper class.
     * @return the instance of the AccountMapper class.
     */
    public static AccountMapper getInstance() {
        if (accountMapper == null) {
            accountMapper = new AccountMapper();
        }
        return accountMapper;
    }

    @Override
    public Account convertFromDTO(AccountDTO accountDTO) {
        return Account.builder()
                .username(accountDTO.getUsername())
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .email(accountDTO.getEmail())
                .password(accountDTO.getPassword())
                .build();
    }

    @Override
    public AccountDTO convertToDTO(Account account) {
        return AccountDTO.builder()
                .username(account.getUsername())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .password(account.getPassword())
                .build();
    }
}
