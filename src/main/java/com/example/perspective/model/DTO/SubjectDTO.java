package com.example.perspective.model.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    @NonNull
    private String name;
}
