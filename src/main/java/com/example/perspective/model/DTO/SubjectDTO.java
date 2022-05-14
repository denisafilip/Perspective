package com.example.perspective.model.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class SubjectDTO {
    @NonNull
    private String name;

    private String description;
}
