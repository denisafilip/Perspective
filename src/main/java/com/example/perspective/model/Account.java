package com.example.perspective.model;

import lombok.*;

import javax.persistence.*;

/**
 * Class for the account of a user created on the food delivery platform.
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Account {

    /**
     * The identifier in the database table.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_user", updatable = false, unique = true, nullable = false)
    private Integer idUser;

    /**
     * The username of the user.
     */
    @NonNull
    @Column(name = "username", unique = true, length = 100, nullable = false)
    private String username;

    /**
     * The first name of the user.
     */
    @NonNull
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    /**
     * The last name of the user.
     */
    @NonNull
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    /**
     * The email of the user.
     */
    @NonNull
    @Column(name = "email", unique = true, length = 100, nullable = false)
    private String email;

    /**
     * The plain text password of the user, which is encrypted in the database table.
     */
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;
}
