package com.example.perspective.model.DTO;

import com.example.perspective.model.Expert;
import com.example.perspective.model.ResourceType;
import com.example.perspective.model.Topic;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    @NonNull
    private String name;

    @NonNull
    private ResourceType type;

    @NonNull
    private Topic topic;

    @NonNull
    private Expert expert;
}
