package com.example.perspective.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for the account of the administrator of a restaurant, a specific type of account.
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "administrator")
public class Administrator extends Account {

    /**
     * Constructor which builds an administrator instance.
     * @param username of the administrator
     * @param firstName of the administrator
     * @param lastName of the administrator
     * @param email of the administrator
     * @param password of the administrator
     */
    @Builder(builderMethodName = "AdminBuilder")
    public Administrator(@NonNull String username, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        super(username, firstName, lastName, email, password);
    }
}
