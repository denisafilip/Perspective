package com.example.perspective.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResourceResponseDTO {
    @NonNull
    private String name;

    @NonNull
    private String url;

    @NonNull
    private String type;

    @NonNull
    private long size;

    @NonNull
    private SubjectDTO subjectDTO;
}
