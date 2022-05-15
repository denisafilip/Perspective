package com.example.perspective.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Class for the account of a customer, which is a specific type of account.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User extends Account {

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Perspective> perspectives;

    /**
     * Constructor used for creating an user instance.
     * @param username of the user
     * @param firstName of the user
     * @param lastName of the user
     * @param email of the user
     * @param password of the user
     */
    @Builder(builderMethodName = "UserBuilder")
    public User(@NonNull String username, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        super(username, firstName, lastName, email, password);
    }
}
