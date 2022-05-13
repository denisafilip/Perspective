package com.example.perspective.model.mappers;

import com.example.perspective.model.DTO.UserDTO;
import com.example.perspective.model.User;

/**
 * Singleton class used for mapping a User to a UserDTO and vice-versa.
 */
public class UserMapper implements Mapper<User, UserDTO> {

    /**
     * Singleton instance of the UserMapper class.
     */
    private static UserMapper userMapper = null;

    /**
     * Constructor.
     */
    private UserMapper() {

    }

    /**
     * Retrieves the single instance of the UserMapper class.
     * @return the instance of the UserMapper class.
     */
    public static UserMapper getInstance() {
        if (userMapper == null) {
            userMapper = new UserMapper();
        }
        return userMapper;
    }

    @Override
    public User convertFromDTO(UserDTO userDTO) {
        return User.UserBuilder()
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return UserDTO.UserDTOBuilder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }


}
