package com.example.perspective.model.DTO;

import com.example.perspective.model.Subject;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class ExpertDTO extends AccountDTO {

    @NonNull
    private List<Subject> subjects;

    @Builder(builderMethodName = "ExpertDTOBuilder")
    public ExpertDTO(@NonNull String username,
                     @NonNull String firstName,
                     @NonNull String lastName,
                     @NonNull String email,
                     @NonNull String password,
                     List<Subject> subjects) {
        super(username, firstName, lastName, email, password);
        this.subjects = subjects;
    }
}
