package com.example.perspective.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class AdministratorDTO extends AccountDTO {

    @Builder(builderMethodName = "AdministratorDTOBuilder")
    public AdministratorDTO(@NonNull String username,
                            @NonNull String firstName,
                            @NonNull String lastName,
                            @NonNull String email,
                            @NonNull String password) {
        super(username, firstName, lastName, email, password);
    }
}
