package com.example.perspective.model.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class ExpertDTO extends AccountDTO {

    @NonNull
    private List<SubjectDTO> subjects;

    @Builder(builderMethodName = "ExpertDTOBuilder")
    public ExpertDTO(@NonNull String firstName,
                     @NonNull String lastName,
                     @NonNull String username,
                     @NonNull String email,
                     @NonNull String password,
                     List<SubjectDTO> subjects) {
        super(firstName, lastName, username, email, password);
        this.subjects = subjects;
    }
}
