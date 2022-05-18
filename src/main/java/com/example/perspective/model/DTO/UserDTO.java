package com.example.perspective.model.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class UserDTO extends AccountDTO {

    @Builder(builderMethodName = "UserDTOBuilder")
    public UserDTO(@NonNull String firstName,
                   @NonNull String lastName,
                   @NonNull String username,
                   @NonNull String email,
                   @NonNull String password) {
        super(firstName, lastName, username, email, password);
    }
}