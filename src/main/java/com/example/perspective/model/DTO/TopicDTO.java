package com.example.perspective.model.DTO;

import com.example.perspective.model.Subject;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Subject subject;
}
