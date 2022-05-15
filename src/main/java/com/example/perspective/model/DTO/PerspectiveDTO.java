package com.example.perspective.model.DTO;

import com.example.perspective.model.Spectrum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PerspectiveDTO {

    @NonNull
    private Spectrum belief;

    @NonNull
    private TopicDTO topicDTO;

    @NonNull
    private UserDTO userDTO;

    private String notes;
}
