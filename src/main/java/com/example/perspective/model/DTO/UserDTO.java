package com.example.perspective.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class UserDTO extends AccountDTO {

    @Builder(builderMethodName = "UserDTOBuilder")
    public UserDTO(@NonNull String username,
                   @NonNull String firstName,
                   @NonNull String lastName,
                   @NonNull String email,
                   @NonNull String password) {
        super(username, firstName, lastName, email, password);
    }
}